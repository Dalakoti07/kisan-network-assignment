package com.dalakoti.android.logger.annotation

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class Listed(
    val name: String,
)