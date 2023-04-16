package com.dalakoti.network.kisan.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object JsonProvider {

    @Singleton
    @Provides
    fun providesJsonConvertor(): Json {
        return Json {
            isLenient = true
            ignoreUnknownKeys = true
            coerceInputValues= true
        }
    }
}