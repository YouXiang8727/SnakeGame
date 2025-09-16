package com.youxiang8727.snake.ui.gameboard.mvi

import androidx.annotation.IntRange
import com.youxiang8727.snake.core.mvi.UiState
import com.youxiang8727.snake.domain.model.Direction
import com.youxiang8727.snake.domain.model.GameDifficulty
import com.youxiang8727.snake.domain.model.GameState
import com.youxiang8727.snake.domain.model.Snake

data class GameBoardUiState(
    @IntRange(from = 10, to = 25)
    val boardSize: Int = 15,
    val difficulty: GameDifficulty = GameDifficulty.EASY,
    val gameState: GameState = GameState.INIT,
    val cells: List<Pair<Int, Int>> = Array(boardSize) { y ->
        Array(boardSize) { x ->
            Pair(x, y)
        }
    }.flatten(),
    val snake: Snake = Snake(),
    val food: Pair<Int, Int> = cells.drop(1).random(),
    val direction: Direction = Direction.RIGHT
): UiState {
    val score: Int = snake.body.size - 1
}
