package com.youxiang8727.snake.ui.gameboard.mvi

import com.youxiang8727.snake.core.mvi.Reducer
import com.youxiang8727.snake.domain.model.GameState

class GameBoardReducer(): Reducer<GameBoardUiState, GameBoardUiEvent>() {
    override fun reduce(state: GameBoardUiState, event: GameBoardUiEvent): GameBoardUiState {
        return when (event) {
            GameBoardUiEvent.NewGame -> GameBoardUiState(
                boardSize = state.boardSize,
                difficulty = state.difficulty
            )

            GameBoardUiEvent.StartGame -> state.copy(gameState = GameState.START)

            GameBoardUiEvent.PauseGame -> state.copy(gameState = GameState.PAUSE)

            GameBoardUiEvent.ResumeGame -> state.copy(gameState = GameState.START)

            GameBoardUiEvent.GameOver -> state.copy(gameState = GameState.GAME_OVER)

            is GameBoardUiEvent.SelectDifficulty -> state.copy(difficulty = event.difficulty)

            is GameBoardUiEvent.SetBoardSize -> state.copy(boardSize = event.size)

            is GameBoardUiEvent.ChangeDirection -> state.copy(
                direction = event.direction
            )

            is GameBoardUiEvent.MoveSnake -> state.copy(
                snake = event.snake
            )

            is GameBoardUiEvent.NewFood -> state.copy(
                food = event.food
            )
        }
    }
}