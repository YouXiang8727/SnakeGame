package com.youxiang8727.snake.core.mvi

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class MviViewModel<S: UiState, E: UiEvent>(
    initialState: S,
    private val reducer: Reducer<S, E>
) : ViewModel() {
    private val bufferSize: Int = 64
    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    private val event: MutableSharedFlow<E> = MutableSharedFlow(
        replay = 0,
        extraBufferCapacity = bufferSize
    )

    fun dispatch(event: E) {
        this.event.tryEmit(event)
    }

    init {
        viewModelScope.launch {
            event.collect { e ->
                Log.d("$this@MviViewModel", "event: $e")
                _state.value = reducer.reduce(state.value, e)
            }
        }

        viewModelScope.launch {
            state.collect { s ->
                Log.d("$this@MviViewModel", "state: ${s}")
            }
        }
    }
}