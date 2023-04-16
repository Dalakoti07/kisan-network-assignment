package com.dalakoti.network.kisan.di

import androidx.viewbinding.BuildConfig
import com.dalakoti.network.core.data.network.TwilioService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val BASE_URL = "https://api.twilio.com/2010-04-01/Accounts/AC4cc294ece32388686d83aa35f9730ed0/"

fun createOkHttpClient(): OkHttpClient {
    // val base64Val = "AC4cc294ece32388686d83aa35f9730ed0:[8c5a9fcc0403432c3863f4886b69e6ff]"
    val base64Val = "QUM0Y2MyOTRlY2UzMjM4ODY4NmQ4M2FhMzVmOTczMGVkMDpbOGM1YTlmY2MwNDAzNDMyYzM4NjNmNDg4NmI2OWU2ZmZd"
    val headerInjector = Interceptor { chain ->
        return@Interceptor chain.proceed(
            chain.request()
                .newBuilder()
                .header(
                    "Authorization",
                    "Basic $base64Val"
                )
                .build()
        )
    }
    return OkHttpClient.Builder().apply {
        addInterceptor(headerInjector)
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor =
                HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            addInterceptor(httpLoggingInterceptor)
        }
        readTimeout(120, TimeUnit.SECONDS)
        writeTimeout(120, TimeUnit.SECONDS)
    }.build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
        coerceInputValues = true
    }
    val contentType = "application/json".toMediaType()
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(
            json.asConverterFactory(contentType)
        )
        .build()
    return retrofit.create(T::class.java)
}

@dagger.Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient =
        createOkHttpClient()

    @Singleton
    @Provides
    fun providesProfileService(okHttpClient: OkHttpClient): TwilioService =
        createWebService(okHttpClient, BASE_URL)

}