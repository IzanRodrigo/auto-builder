package app.izantech.plugin.autobuilder.processor.model

import app.izantech.plugin.autobuilder.annotation.DefaultValue
import app.izantech.plugin.autobuilder.processor.util.annotationOrNull
import app.izantech.plugin.autobuilder.processor.util.defaultValueOrNull
import app.izantech.plugin.autobuilder.processor.util.instanceOf
import app.izantech.plugin.autobuilder.processor.util.toKAnnotations
import com.google.devtools.ksp.isPublic
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.ksp.toTypeName

internal class AutoBuilderProperty private constructor(
    val name: String,
    val typeName: TypeName,
    val annotations: Iterable<AnnotationSpec>,
    val isMutable: Boolean,
    val hasCustomDefaultValue: Boolean,
    val resolvedType: KSType,
    val defaultValue: String?,
) {
    companion object {
        context(Resolver)
        fun from(declaration: KSPropertyDeclaration): AutoBuilderProperty? {
            if (!declaration.isPublic()) return null

            val resolvedType = declaration.type.resolve()
            return AutoBuilderProperty(
                name = declaration.simpleName.asString(),
                typeName = declaration.typeName,
                annotations = declaration.kAnnotations,
                isMutable = declaration.isMutable,
                hasCustomDefaultValue = declaration.getter?.annotationOrNull<DefaultValue>() != null,
                resolvedType = resolvedType,
                defaultValue = resolvedType.defaultValueOrNull,
            )
        }
    }

    val isNullable: Boolean
        get() = typeName.isNullable
}

context(Resolver)
private val KSPropertyDeclaration.kAnnotations
    get() = getter
        ?.annotations
        ?.filterNot { it.instanceOf<DefaultValue>() }
        .toKAnnotations()

private val KSPropertyDeclaration.typeName: TypeName
    get() {
        val typeName = type.toTypeName()
        val annotations = typeName.annotations + getter?.returnType?.annotations?.toKAnnotations().orEmpty()
        return typeName.copy(annotations = annotations)
    }
