package com.dalakoti.network.core.domain.state

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()
    object ParsingError : Failure()
    object UnauthorizedError : Failure()

    abstract class FeatureFailure : Failure()
}

@Serializable
data class ErrorResponse(
    var message: String?,
    var error: String? = null,
    @Contextual
    var exception: Exception? = null,
    var statusCode: Float? = null
)

sealed class Resource<T>(val data: T? = null, val error: Pair<Failure, ErrorResponse>? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(error: Pair<Failure, ErrorResponse>?, data: T? = null) : Resource<T>(data, error)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}
