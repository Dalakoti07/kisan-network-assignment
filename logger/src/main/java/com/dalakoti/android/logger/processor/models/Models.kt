package com.dalakoti.android.logger.processor.models

internal data class ProcessedFunction(
    val imports: List<String>,
    val text: String,
)

internal fun kspPackage() = "com.dalakoti.android"
