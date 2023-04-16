package com.dalakoti.network.kisan.features.sent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dalakoti.network.core.data.room.dao.SmsSentDao
import com.dalakoti.network.core.data.room.entities.SmsSentEntity
import com.dalakoti.network.core.domain.vm.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

data class SmsSentState(
    val list: List<SmsSentEntity> = emptyList(),
)

@HiltViewModel
class SmsSentViewModel @Inject constructor(
    private val smsSentDao: SmsSentDao,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(SmsSentState())
    val uiState: StateFlow<SmsSentState>
        get() = _uiState

    init {
        smsSentDao.getAllSentSms().onEach {
            _uiState.value = _uiState.value.copy(
                list = it
            )
        }.launchIn(viewModelScope)
    }
}