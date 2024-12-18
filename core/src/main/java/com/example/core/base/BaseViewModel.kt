package com.example.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<Event : UiEvent, State : UiState, Effect : UiSideEffect>(initialState: State) :
    ViewModel() {

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    private val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    // Get Current State
    val currentState: State
        get() = _uiState.value

    protected fun updateState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }

    protected fun emitEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

    protected abstract suspend fun handleEvent(event: Event)

    fun dispatchEvent(event: Event) {
        viewModelScope.launch {
            handleEvent(event)
        }
    }
}
