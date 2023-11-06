package com.dalakoti.android.logger.processor

import com.dalakoti.android.logger.annotation.Listed
import com.dalakoti.android.logger.processor.visitor.ListedVisitor
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.validate
import kotlin.reflect.KClass

internal class ListedProcessor(
    private val fileGenerator: FileWriter,
) : SymbolProcessor {

    private val listVisitor = ListedVisitor()
    override fun process(resolver: Resolver): List<KSAnnotated> {

        val listedFunctions: Sequence<KSFunctionDeclaration> =
            resolver.findAnnotations(Listed::class)
        if (!listedFunctions.iterator().hasNext()) return emptyList()

        fileGenerator.createFile(listedFunctions, Listed::class, "GeneratedLists", "listOf") {
            it.accept(listVisitor, Unit)
        }
        return (listedFunctions).filterNot { it.validate() }.toList()
    }

    private fun Resolver.findAnnotations(
        kClass: KClass<*>,
    ) = getSymbolsWithAnnotation(
        kClass.qualifiedName.toString())
        .filterIsInstance<KSFunctionDeclaration>().filter {
            it.parameters.isEmpty()
        }
}
