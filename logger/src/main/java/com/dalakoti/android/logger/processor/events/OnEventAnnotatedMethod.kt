package com.dalakoti.android.logger.processor.events

import javax.lang.model.element.ExecutableElement

class OnEventAnnotatedMethod(
    private val element: ExecutableElement
) {
    private val event = "Some rubbish name"

    fun getEvent(): String = event

    fun getMethodName(): String = element.simpleName.toString()

    fun getPackageName(): String {
        return getQualifiedName().replace(
            ".${getClassSimpleName()}",
            ""
        )
    }

    fun getClassSimpleName() = element.enclosingElement.simpleName.toString()

    fun getQualifiedName() = element.enclosingElement.toString()
}