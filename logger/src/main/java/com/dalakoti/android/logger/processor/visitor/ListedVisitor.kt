package com.dalakoti.android.logger.processor.visitor

import com.dalakoti.android.logger.processor.models.ProcessedFunction
import com.dalakoti.android.logger.processor.utils.declaration
import com.dalakoti.android.logger.processor.utils.imports
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.visitor.KSEmptyVisitor

internal class ListedVisitor() :
    KSEmptyVisitor<Unit, ProcessedFunction?>() {

    override fun defaultHandler(node: KSNode, data: Unit): ProcessedFunction? {
        return null
    }

    override fun visitFunctionDeclaration(
        function: KSFunctionDeclaration,
        data: Unit,
    ): ProcessedFunction? {
        return ProcessedFunction(
            function.imports(),
            function.declaration()
        )
    }
}