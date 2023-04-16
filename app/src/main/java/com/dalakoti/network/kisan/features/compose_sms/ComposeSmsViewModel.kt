package com.dalakoti.network.kisan.features.compose_sms

import android.util.Log
import androidx.lifecycle.viewModelScope
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
    private val sendSmsUseCase: SendSmsUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(ComposeSmsState())
    val uiState: StateFlow<ComposeSmsState>
        get() = _uiState

    fun sendSms(to: String, body: String) {
        if (body.isEmpty()) {
            viewModelScope.launch {
                _events.send(UiEvents.ShowSnackBar("Body is empty"))
            }
            return
        }
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

}