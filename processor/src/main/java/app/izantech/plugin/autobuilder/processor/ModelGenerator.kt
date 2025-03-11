package app.izantech.plugin.autobuilder.processor

import app.izantech.plugin.autobuilder.processor.util.addOptionalOriginatingKSFile
import app.izantech.plugin.autobuilder.processor.util.autoBuilderAnnotation
import app.izantech.plugin.autobuilder.processor.util.autoBuilderPropertyAnnotation
import app.izantech.plugin.autobuilder.processor.util.defaultValueOrNull
import app.izantech.plugin.autobuilder.processor.util.getProperties
import app.izantech.plugin.autobuilder.processor.util.isArray
import app.izantech.plugin.autobuilder.processor.util.isCharSequence
import app.izantech.plugin.autobuilder.processor.util.isString
import app.izantech.plugin.autobuilder.processor.util.kAnnotations
import app.izantech.plugin.autobuilder.processor.util.prettyPrint
import app.izantech.plugin.autobuilder.processor.util.suppressWarningTypes
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import com.squareup.kotlinpoet.ANY
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.LambdaTypeName
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.MemberName.Companion.member
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.UNIT
import com.squareup.kotlinpoet.ksp.toTypeName
import com.squareup.kotlinpoet.ksp.writeTo

private typealias ModelProperties = Iterable<KSPropertyDeclaration>
private typealias ModelAnnotations = Iterable<AnnotationSpec>

class ModelGenerator(
    private val resolver: Resolver,
    private val codeGenerator: CodeGenerator,
) : KSVisitorVoid() {
    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        val originatingFile = classDeclaration.containingFile
        val packageName = classDeclaration.packageName.asString()
        val symbolName = classDeclaration.simpleName.asString()
        val className = ClassName(packageName, symbolName)
        val implClassName = ClassName(packageName, "${symbolName}Impl")
        val autoBuilderAnnotation = classDeclaration.autoBuilderAnnotation
        val properties = classDeclaration.getProperties(
            useInherited = autoBuilderAnnotation.inheritedProperties,
        )
        val annotations = classDeclaration.kAnnotations
        val builderClassName = ClassName(packageName, "${symbolName}Builder")

        FileSpec.builder(packageName, "${symbolName}.builder")
            .suppressWarningTypes("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate")
            .addType(generateImplementation(originatingFile, properties, annotations, className, implClassName))
            .addType(generateBuilder(originatingFile, properties, className, implClassName))
            .addFunctions(generateModelExtensions(originatingFile, className, builderClassName))
            .build()
            .writeTo(codeGenerator, Dependencies(true))
    }

    // region ModelImpl
    private fun generateImplementation(
        originatingFile: KSFile?,
        properties: ModelProperties,
        annotations: ModelAnnotations,
        className: ClassName,
        implClassName: ClassName,
    ): TypeSpec {
        return TypeSpec.classBuilder(implClassName)
            .addAnnotations(annotations)
            .addModifiers(KModifier.PRIVATE)
            .addSuperinterface(className)
            .primaryConstructor(generateImplementationPrimaryConstructor(properties))
            .addProperties(generateImplementationProperties(properties))
            .addFunction(generateImplementationEquals(properties, className))
            .addFunction(generateImplementationHashCode(properties))
            .addFunction(generateImplementationToString(properties, className))
            .addOptionalOriginatingKSFile(originatingFile)
            .build()
    }

    private fun generateImplementationPrimaryConstructor(properties: ModelProperties) =
        FunSpec.constructorBuilder()
            .addParameters(properties.map { property ->
                val name = property.simpleName.asString()
                val type = property.type.toTypeName()
                ParameterSpec.builder(name, type)
                    .addAnnotations(property.kAnnotations)
                    .build()
            })
            .build()

    private fun generateImplementationProperties(properties: ModelProperties) =
        properties.map { property ->
            val name = property.simpleName.asString()
            val type = property.type.toTypeName()
            PropertySpec.builder(name, type)
                .initializer(name)
                .addModifiers(KModifier.OVERRIDE)
                .mutable(property.isMutable)
                .build()
        }

    private fun generateImplementationEquals(
        properties: ModelProperties,
        className: ClassName,
    ) = with(resolver) {
        val args = properties.joinToString(" &&\n\t") {
            val name = it.simpleName.asString()
            if (it.type.resolve().isArray) {
                "this.$name.contentEquals(other.$name)"
            } else {
                "this.$name == other.$name"
            }
        }
        FunSpec.builder("equals")
            .addModifiers(KModifier.OVERRIDE)
            .addParameter("other", ANY.copy(nullable = true))
            .returns(Boolean::class)
            .addStatement("if (this === other) return true")
            .addStatement("if (javaClass != other?.javaClass) return false")
            .addStatement("other as %T", className)
            .addStatement("return %L", args)
            .build()
    }

    private fun generateImplementationHashCode(properties: ModelProperties): FunSpec {
        val args = properties.prettyPrint { "${it.simpleName.asString()}," }
        val javaHash = ClassName("java.util", "Objects").member("hash")
        return FunSpec.builder("hashCode")
            .addModifiers(KModifier.OVERRIDE)
            .returns(Int::class)
            .addStatement("return \n\t%M(%L)", javaHash, args)
            .build()
    }

    private fun generateImplementationToString(
        properties: ModelProperties,
        className: ClassName,
    ) = with(resolver) {
        val args = buildList {
            add("append(\"${className.simpleName}(\")")
            addAll(properties.mapIndexed { index, it ->
                val name = it.simpleName.asString()
                val content = if (it.type.resolve().isArray) {
                    "{${name}.contentToString()}"
                } else {
                    name
                }
                val trailingComma = if (index < properties.count() - 1) "," else ""
                "append(\"$name=\$${content}${trailingComma}\")"
            })
            add("append(\")\")")
        }
        val formattedArgs = args.joinToString(
            prefix = "\t",
            separator = "\n\t"
        )

        FunSpec.builder("toString")
            .addModifiers(KModifier.OVERRIDE)
            .returns(String::class)
            .addStatement("return buildString {\n%L\n}", formattedArgs)
            .build()
    }
    // endregion

    // region Builder
    private fun generateBuilder(
        originatingFile: KSFile?,
        properties: ModelProperties,
        className: ClassName,
        implClassName: ClassName,
    ): TypeSpec {
        val builderClassName = ClassName(className.packageName, "${className.simpleName}Builder")
        val builderProperties = generateBuilderProperties(properties)
        val constructorParameters = builderProperties.prettyPrint { builderProperty ->
            // Edge case:
            //  Builder properties can be nullable even if the symbol property is not.
            //  This may happen when the we can't infer the default value of the symbol property.
            val symbolProperty = properties
                .first { it.simpleName.asString() == builderProperty.name }
            if (!symbolProperty.type.resolve().isMarkedNullable && builderProperty.type.isNullable) {
                "${builderProperty.name} = requireNotNull(${builderProperty.name}),"
            } else {
                "${builderProperty.name} = ${builderProperty.name},"
            }
        }
        return TypeSpec.classBuilder(builderClassName)
            .primaryConstructor(
                FunSpec.constructorBuilder()
                    .addModifiers(KModifier.INTERNAL)
                    .addAnnotation(PublishedApi::class)
                    .addParameter(
                        ParameterSpec.builder("source", className.copy(nullable = true))
                            .defaultValue("null")
                            .build()
                    )
                    .build()
            )
            .addProperties(builderProperties)
            .addFunctions(generateBuilderJavaMethods(builderClassName, properties))
            .addFunction(
                FunSpec.builder("build")
                    .returns(className)
                    .addStatement("return \n\t%T(%L)", implClassName, constructorParameters)
                    .build()
            )
            .addOptionalOriginatingKSFile(originatingFile)
            .build()
    }

    private fun generateBuilderProperties(properties: ModelProperties) = properties.map { property ->
        val name = property.simpleName.asString()
        val type = property.type.resolve()
        val defaultValue = getPropertyDefaultValue(property)
        val typeName = type.toTypeName()
            .copy(nullable = type.isMarkedNullable || defaultValue == null)
        PropertySpec.builder(name, typeName)
            .mutable()
            .let {
                when {
                    typeName.isNullable -> it.initializer("source?.%L", name)
                    defaultValue != null -> it.initializer(
                        "source?.%L ?: %L",
                        name,
                        defaultValue
                    )

                    else -> it // Won't happen because 'defaultValue' was already checked.
                }
            }
            .setter(FunSpec.setterBuilder().addAnnotation(JvmSynthetic::class).build())
            .addAnnotations(property.kAnnotations)
            .build()
    }

    private fun generateBuilderJavaMethods(builderClassName: ClassName, properties: ModelProperties) = properties.map { property ->
        val name = property.simpleName.asString()
        val type = property.type.toTypeName()
        FunSpec.builder(name)
            .addParameter(name, type)
            .addStatement("return %M { this.$name = $name }", MemberName("kotlin", "apply"))
            .returns(builderClassName)
            .build()
    }

    private fun getPropertyDefaultValue(property: KSPropertyDeclaration): String? = with(resolver) {
        val type = property.type.resolve()

        // Return the default value from the annotation if it exists.
        val defaultPropertyValue = property.autoBuilderPropertyAnnotation
            ?.defaultValue
            ?.takeIf(String::isNotBlank)
        return if (defaultPropertyValue != null) {
            val isStringLiteral = type.isString || type.isCharSequence
            if (isStringLiteral) "\"$defaultPropertyValue\"" else defaultPropertyValue
        } else {
            // Otherwise try to infer the default value from the type.
            type.defaultValueOrNull
        }
    }
    // endregion

    // region Model extensions
    private fun generateModelExtensions(
        originatingFile: KSFile?,
        className: ClassName,
        builderClassName: ClassName,
    ): Iterable<FunSpec> {
        val initLambdaTypeName = LambdaTypeName.get(receiver = builderClassName, returnType = UNIT)
        val initLambdaParameter = ParameterSpec
            .builder("init", initLambdaTypeName)
            .defaultValue("%L", "{}")
            .build()

        val initializerFunction = FunSpec.builder(className.simpleName)
            .addAnnotation(JvmSynthetic::class)
            .addModifiers(KModifier.INLINE)
            .addParameter(initLambdaParameter)
            .returns(className)
            .addStatement(
                "return %T().apply(${initLambdaParameter.name}).build()",
                builderClassName
            )
            .addOptionalOriginatingKSFile(originatingFile)
            .build()

        val copyFunction = FunSpec.builder("copy")
            .receiver(className)
            .addAnnotation(JvmSynthetic::class)
            .addModifiers(KModifier.INLINE)
            .addParameter(initLambdaParameter)
            .returns(className)
            .addStatement(
                "return %T(this).apply(${initLambdaParameter.name}).build()",
                builderClassName
            )
            .addOptionalOriginatingKSFile(originatingFile)
            .build()

        return listOf(initializerFunction, copyFunction)
    }
    // endregion
}
