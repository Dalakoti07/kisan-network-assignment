package com.dalakoti.android.logger.processor.events

import com.dalakoti.android.logger.annotation.LogData
import com.dalakoti.android.logger.processor.utils.Logger
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind

class OnEventAnnotationValidator(
    private val logger: Logger
) {
    fun isValid(element: Element): Boolean {
        if (element.kind != ElementKind.METHOD) {
            logger.logError(
                element,
                "Only methods can be annotated with @${LogData::class}"
            )
            return false
        }

        return true
    }
}