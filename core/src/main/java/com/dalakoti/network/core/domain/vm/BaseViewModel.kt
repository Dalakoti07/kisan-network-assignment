package com.dalakoti.network.core.domain.vm

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.dalakoti.network.core.domain.state.ErrorResponse
import com.dalakoti.network.core.domain.state.Failure
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

sealed class UiEvents {
    class ShowSnackBar(val string: String) : UiEvents()
    class ShowError(val error: Pair<Failure, ErrorResponse?>) : UiEvents()
}

open class BaseViewModel: ViewModel() {

    protected val _events = Channel<UiEvents>(Channel.BUFFERED)
    val events = _events.receiveAsFlow() // expose as flow

}