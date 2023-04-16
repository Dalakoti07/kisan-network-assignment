package com.dalakoti.network.core.data.network

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TwilioService {

    // https://api.twilio.com/2010-04-01/Accounts/AC4cc294ece32388686d83aa35f9730ed0/Messages.json
    @FormUrlEncoded
    @POST("Messages.json")
    suspend fun sendMessage(
        @Field("To") to: String,
        @Field("From") from: String,
        @Field("Body") body: String,
    ): Response<Unit>

}