package com.dalakoti.network.kisan.features.compose_sms

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dalakoti.network.core.data.models.Contact
import com.dalakoti.network.core.data.network.responses.SendSmsResponse
import com.dalakoti.network.core.data.room.dao.SmsSentDao
import com.dalakoti.network.core.data.room.entities.SmsSentEntity
import com.dalakoti.network.core.domain.state.Resource
import com.dalakoti.network.core.domain.usecases.SendSmsUseCase
import com.dalakoti.network.core.domain.vm.BaseViewModel
import com.dalakoti.network.core.domain.vm.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ComposeSmsState(
    val isLoading: Boolean = false,
)

private const val TAG = "ComposeSmsViewModel"

@HiltViewModel
class ComposeSmsViewModel @Inject constructor(
    private val sendSmsUseCase: SendSmsUseCase,
    private val dao: SmsSentDao,
) : BaseViewModel() {

    private lateinit var personContact: Contact

    private val _uiState = MutableStateFlow(ComposeSmsState())
    val uiState: StateFlow<ComposeSmsState>
        get() = _uiState

    fun sendSms(person: Contact, to: String, body: String) {
        if (body.isEmpty()) {
            viewModelScope.launch {
                _events.send(UiEvents.ShowSnackBar("Body is empty"))
            }
            return
        }
        personContact = person
        viewModelScope.launch {
            sendSmsUseCase(
                to = to,
                body = body,
            ).onEach { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _uiState.value = uiState.value.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        _uiState.value = uiState.value.copy(isLoading = false)
                        _events.send(UiEvents.ShowSnackBar("SMS sent"))
                        makeEntryInDb(resource.data)
                    }
                    is Resource.Error -> {
                        _uiState.value = uiState.value.copy(isLoading = false)
                        _events.send(
                            UiEvents.ShowSnackBar(
                                resource.error?.second?.message ?: "unknown error"
                            )
                        )
                    }
                }
            }.launchIn(this)
        }

    }

    private fun makeEntryInDb(smsResponse: SendSmsResponse?) {
        smsResponse?: return
        viewModelScope.launch {
            dao.insertIntoDb(
                SmsSentEntity(
                    sid = smsResponse.sid,
                    mobileNumber = smsResponse.to,
                    sendToName = personContact.name,
                    uri = smsResponse.uri,
                    dateAndTime = smsResponse.date_created,
                    body = smsResponse.body,
                )
            )
        }
    }

}