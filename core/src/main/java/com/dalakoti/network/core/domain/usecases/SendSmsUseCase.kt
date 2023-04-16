package com.dalakoti.network.core.domain.usecases

import com.dalakoti.network.core.data.network.responses.SendSmsResponse
import com.dalakoti.network.core.data.repository.TwilioRepository
import com.dalakoti.network.core.domain.state.Resource
import com.dalakoti.network.core.domain.utils.returnAuthOrServerErrorResponse
import com.dalakoti.network.core.domain.utils.returnExceptionResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class SendSmsUseCase @Inject constructor(
    private val twilioRepository: TwilioRepository,
    private val jsonConvertor: Json
) {

    suspend operator fun invoke(to: String, body: String): Flow<Resource<SendSmsResponse>> =
        withContext(Dispatchers.IO){
            flow {
                try {
                    emit(Resource.Loading())
                    val result = twilioRepository.sendOtp(
                        to,
                        body
                    )
                    if(result.isSuccessful){
                        emit(Resource.Success(result.body()!!))
                    }else{
                        emit(
                            returnAuthOrServerErrorResponse(
                                result,
                                jsonConvertor
                            )
                        )
                    }
                }catch (exception: Exception){
                    emit(
                        returnExceptionResponse(
                            exception
                        )
                    )
                }
            }
        }
}