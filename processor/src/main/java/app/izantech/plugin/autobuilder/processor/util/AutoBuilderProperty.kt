@file:OptIn(KspExperimental::class)

package app.izantech.plugin.autobuilder.processor.util

import app.izantech.plugin.autobuilder.annotation.AutoBuilder.Property
import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.getAnnotationsByType
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.ksp.toTypeName

internal typealias ModelProperty = AutoBuilderProperty
internal typealias ModelProperties = Iterable<ModelProperty>
internal typealias ModelAnnotations = Iterable<AnnotationSpec>

internal class AutoBuilderProperty private constructor(
    val name: String,
    val typeName: TypeName,
    val annotations: Iterable<AnnotationSpec>,
    val isMutable: Boolean,
    val propertyAnnotation: Property?,
    val resolvedType: KSType,
) {
    companion object {
        fun from(declaration: KSPropertyDeclaration): AutoBuilderProperty? {
            val propertyAnnotation = declaration.propertyAnnotation
            if (propertyAnnotation?.ignored == true) return null

            return AutoBuilderProperty(
                name = declaration.simpleName.asString(),
                typeName = declaration.typeName,
                annotations = declaration.kAnnotations,
                isMutable = declaration.isMutable,
                propertyAnnotation = propertyAnnotation,
                resolvedType = declaration.type.resolve(),
            )
        }
    }

    val isNullable: Boolean
        get() = typeName.isNullable
}

private val KSPropertyDeclaration.kAnnotations
    get() = getter?.annotations.toKAnnotations()

private val KSPropertyDeclaration.propertyAnnotation
    get() = getAnnotationsByType(Property::class).firstOrNull()

private val KSPropertyDeclaration.typeName: TypeName
    get() {
        val typeName = type.toTypeName()
        val annotations = typeName.annotations + getter?.returnType?.annotations?.toKAnnotations().orEmpty()
        return typeName.copy(annotations = annotations)
    }
