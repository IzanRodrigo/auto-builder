package app.izantech.plugin.autobuilder.processor.util

import app.izantech.plugin.autobuilder.annotation.AutoBuilder
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.ksp.addOriginatingKSFile
import com.squareup.kotlinpoet.ksp.toAnnotationSpec

internal fun FileSpec.Builder.suppressWarningTypes(vararg types: String): FileSpec.Builder {
    if (types.isEmpty()) {
        return this
    }

    val format = types.joinToString(
        prefix = "\n",
        separator = "",
        transform = { "\t%S,\n" },
    )
    return addAnnotation(
        AnnotationSpec.builder(Suppress::class)
            .addMember(format, *types)
            .build()
    )
}

internal fun TypeSpec.Builder.addOptionalOriginatingKSFile(file: KSFile?) =
    file?.let(::addOriginatingKSFile) ?: this

internal fun FunSpec.Builder.addOptionalOriginatingKSFile(file: KSFile?) =
    file?.let(::addOriginatingKSFile) ?: this

internal fun FunSpec.Builder.hideFromKotlin(): FunSpec.Builder {
    // Hide the method from Kotlin code.
    // This workaround is needed because there's no @JavaOnly annotation available:
    // https://youtrack.jetbrains.com/issue/KT-36439
    return addAnnotation(AnnotationSpec.builder(SinceKotlin::class)
        .addMember("%S", "99999999.9")
        .build())
}

internal val KSClassDeclaration.kAnnotations
    get() = annotations.toKAnnotations()

internal fun Sequence<KSAnnotation>?.toKAnnotations() = orEmpty()
    .filter { it.shortName.asString() != AutoBuilder::class.simpleName }
    .map(KSAnnotation::toAnnotationSpec)
    .toList()
