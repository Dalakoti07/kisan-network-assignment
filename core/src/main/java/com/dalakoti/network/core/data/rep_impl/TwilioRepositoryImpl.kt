package com.dalakoti.network.core.data.rep_impl

import com.dalakoti.network.core.data.network.TwilioService
import com.dalakoti.network.core.data.network.responses.SendSmsResponse
import com.dalakoti.network.core.data.repository.TwilioRepository
import retrofit2.Response
import javax.inject.Inject

class TwilioRepositoryImpl @Inject constructor(
    private val service: TwilioService
) : TwilioRepository {

    override suspend fun sendOtp(to: String, body: String): Response<SendSmsResponse> {
        return service.sendMessage(
            to = to,
            from = "+16205221109",
            body = body,
        )
    }
}