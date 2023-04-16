package com.dalakoti.network.core.data.repository

import com.dalakoti.network.core.data.network.responses.SendSmsResponse
import retrofit2.Response

interface TwilioRepository {
    suspend fun sendOtp(to: String, body: String): Response<SendSmsResponse>

}