package app.izantech.plugin.autobuilder.processor

import app.izantech.plugin.autobuilder.annotation.AutoBuilder
import app.izantech.plugin.autobuilder.processor.util.autoBuilderAnnotation
import app.izantech.plugin.autobuilder.processor.util.getProperties
import com.google.devtools.ksp.isPublic
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.validate

class AutoBuilderProcessor(private val environment: SymbolProcessorEnvironment) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val (validatedSymbols, deferredSymbols) = getSymbolsDefinitions(resolver)
            .partition(KSClassDeclaration::validate)
        val modelGenerator = ModelGenerator(resolver, environment.codeGenerator)
        validatedSymbols.forEach { symbol ->
            modelGenerator.visitClassDeclaration(symbol, Unit)
        }
        return deferredSymbols
    }

    private fun getSymbolsDefinitions(resolver: Resolver) = resolver
        .getSymbolsWithAnnotation(AutoBuilder::class.qualifiedName.orEmpty())
        .filterIsInstance<KSClassDeclaration>()
        .filter { symbol ->
            // Check if the definition is a public interface.
            val isPublicInterface = symbol.classKind == ClassKind.INTERFACE && symbol.isPublic()
            if (!isPublicInterface) {
                environment.logger.error(
                    "This model definition must be a public interface containing at least one property.",
                    symbol
                )
                return@filter false
            }

            // Check if the interface has properties.
            val hasProperties = symbol.getProperties(
                useInherited = symbol.autoBuilderAnnotation.inheritedProperties,
            ).any()
            if (!hasProperties) {
                environment.logger.error(
                    "This model definition must contain at least one property.",
                    symbol
                )
                return@filter false
            }

            true
        }
}
