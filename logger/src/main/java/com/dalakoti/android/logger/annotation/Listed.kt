package com.dalakoti.android.logger.annotation

// todo why is this not working?
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class Listed(
    val name: String,
)