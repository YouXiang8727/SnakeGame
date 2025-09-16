package com.youxiang8727.snake.ui.gameboard.mvi

import com.youxiang8727.snake.core.mvi.UiEvent
import com.youxiang8727.snake.domain.model.Direction
import com.youxiang8727.snake.domain.model.GameDifficulty
import com.youxiang8727.snake.domain.model.Snake

sealed interface GameBoardUiEvent: UiEvent {
    data object NewGame : GameBoardUiEvent

    data object StartGame : GameBoardUiEvent

    data object PauseGame : GameBoardUiEvent

    data object ResumeGame : GameBoardUiEvent

    data object GameOver : GameBoardUiEvent

    data class SetBoardSize(val size: Int) : GameBoardUiEvent

    data class SelectDifficulty(val difficulty: GameDifficulty) : GameBoardUiEvent

    data class ChangeDirection(val direction: Direction) : GameBoardUiEvent

    data class MoveSnake(val snake: Snake) : GameBoardUiEvent

    data class NewFood(val food: Pair<Int, Int>) : GameBoardUiEvent
}