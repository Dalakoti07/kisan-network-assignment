package com.dalakoti.network.kisan.core.repository

import com.dalakoti.network.core.data.network.TwilioService
import com.dalakoti.network.core.data.network.responses.SendSmsResponse
import com.dalakoti.network.core.domain.state.ErrorResponse
import com.dalakoti.network.kisan.core.utils.BaseFakeService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class FakeTwilioService : TwilioService, BaseFakeService() {

    private val stringResponse = readJson("assets/success_response.json")

    private lateinit var response: SendSmsResponse

    private val jsonConvertor = Json {
        isLenient = true
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    companion object {
        var throwError = false
        var authFailure = false
    }

    override suspend fun sendMessage(
        to: String,
        from: String,
        body: String
    ): Response<SendSmsResponse> {
        if (throwError) {
            throw Exception("Twilio is down")
        }
        if (authFailure) {
            return Response.error<SendSmsResponse>(
                401,
                Json.encodeToString(ErrorResponse.serializer(), ErrorResponse("Auth error"))
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
        }
        val resultObject =
            jsonConvertor.decodeFromString(SendSmsResponse.serializer(), stringResponse)
        println("returning response")
        return Response.success(resultObject)
    }


}