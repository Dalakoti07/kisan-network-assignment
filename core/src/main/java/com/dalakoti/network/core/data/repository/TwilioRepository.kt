package com.dalakoti.network.core.data.repository

import retrofit2.Response

interface TwilioRepository {

    suspend fun sendOtp(to: String, body: String): Response<Unit>

}