package com.youxiang8727.snake.core.mvi

abstract class Reducer<S: UiState, E: UiEvent> {
    abstract fun reduce(state: S, event: E): S
}