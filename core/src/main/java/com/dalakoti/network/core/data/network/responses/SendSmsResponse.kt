package com.dalakoti.network.core.data.network.responses

@kotlinx.serialization.Serializable
data class SendSmsResponse(
    val body: String,
    val uri: String,
    val to: String,
    val sid: String
)