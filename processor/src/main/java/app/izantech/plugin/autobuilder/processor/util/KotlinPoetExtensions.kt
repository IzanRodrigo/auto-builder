package app.izantech.plugin.autobuilder.processor.util

import app.izantech.plugin.autobuilder.annotation.AutoBuilder
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.ksp.addOriginatingKSFile
import com.squareup.kotlinpoet.ksp.toAnnotationSpec

internal fun FileSpec.Builder.suppressWarningTypes(vararg types: String): FileSpec.Builder {
    if (types.isEmpty()) {
        return this
    }

    val format = "%S,".repeat(types.count()).trimEnd(',')
    return addAnnotation(
        AnnotationSpec.builder(ClassName("", "Suppress"))
            .addMember(format, *types)
            .build()
    )
}

internal fun TypeSpec.Builder.addOptionalOriginatingKSFile(file: KSFile?) =
    file?.let(::addOriginatingKSFile) ?: this

internal fun FunSpec.Builder.addOptionalOriginatingKSFile(file: KSFile?) =
    file?.let(::addOriginatingKSFile) ?: this

internal val KSClassDeclaration.kAnnotations
    get() = annotations.toKAnnotations()

internal val KSPropertyDeclaration.kAnnotations
    get() = getter?.annotations.toKAnnotations()

private fun Sequence<KSAnnotation>?.toKAnnotations() = orEmpty()
    .filter { it.shortName.asString() != AutoBuilder::class.simpleName }
    .map(KSAnnotation::toAnnotationSpec)
    .toList()
