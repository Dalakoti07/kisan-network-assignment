package com.dalakoti.network.core.domain.utils

import com.dalakoti.network.core.domain.state.ErrorResponse
import com.dalakoti.network.core.domain.state.Failure
import com.dalakoti.network.core.domain.state.Resource
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import retrofit2.Response

fun <T> returnAuthOrServerErrorResponse(
    response: Response<T>,
    jsonConvertor: Json
): Resource.Error<T> {
    val json = response.errorBody()?.string()
    val error = jsonConvertor.decodeFromString(ErrorResponse.serializer(), json!!)
    //400 status code added to handle cluster change and trigger logout
    return if (response.code() == 401 || response.code() == 400) {
        Resource.Error(Pair(Failure.UnauthorizedError, error))
    } else {
        Resource.Error(
            Pair(
                Failure.ServerError,
                error
            )
        )
    }
}

fun <T> returnExceptionResponse(
    exception: Exception
): Resource.Error<T> {
    return if (exception is SerializationException) {
        Resource.Error(
            Pair(
                Failure.ParsingError,
                ErrorResponse(exception.message, exception = exception)
            )
        )
    } else {
        Resource.Error(
            Pair(
                Failure.NetworkConnection,
                ErrorResponse(exception.message, exception = exception)
            )
        )
    }
}
