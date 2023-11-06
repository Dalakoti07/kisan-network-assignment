package com.dalakoti.android.logger.processor.utils

import javax.annotation.processing.Messager
import javax.lang.model.element.Element
import javax.tools.Diagnostic

class Logger(private val messager: Messager) {

    fun logError(element: Element? = null, message: String? = null) {
        messager.printMessage(Diagnostic.Kind.ERROR, message, element)
    }
}