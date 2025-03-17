package app.izantech.plugin.autobuilder.processor

import com.google.devtools.ksp.containingFile
import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.symbol.KSPropertyDeclaration

internal object AutoBuilderErrors {
    const val TEMPLATE_NOT_PUBLIC_INTERFACE = "The interface '%s' must be a public interface containing at least one property."
    const val TEMPLATE_EMPTY_INTERFACE = "The interface '%s' must contain at least one property."
    const val TEMPLATE_UNINITIALIZED_PROPERTY = "The property '%s' is not nullable and has no default value."

    fun notPublicInterface(declaration: KSClassDeclaration) = errorMessage(declaration) {
        TEMPLATE_NOT_PUBLIC_INTERFACE.format(declaration.simpleName.asString())
    }

    fun emptyInterface(declaration: KSClassDeclaration) = errorMessage(declaration) {
        TEMPLATE_EMPTY_INTERFACE.format(declaration.simpleName.asString())
    }

    fun uninitializedProperty(declaration: KSPropertyDeclaration) = errorMessage(declaration) {
        TEMPLATE_UNINITIALIZED_PROPERTY.format(declaration.simpleName.asString())
    }

    private inline fun errorMessage(declaration: KSNode, message: () -> String): String {
        val location = declaration.location
        val errorPath = if (location is FileLocation) {
            "${declaration.containingFile?.filePath}:${location.lineNumber}"
        } else {
            declaration.containingFile?.filePath ?: "Unknown file"
        }
        return "$errorPath: ${message()}"
    }
}
