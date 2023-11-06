package com.dalakoti.android.logger.processor.provider

import com.dalakoti.android.logger.processor.FileContentGenerator
import com.dalakoti.android.logger.processor.FileWriter
import com.dalakoti.android.logger.processor.ListedProcessor
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

class ListedProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        val fileWriter = FileWriter(FileContentGenerator(), environment.codeGenerator)
        return ListedProcessor(fileWriter)
    }
}

