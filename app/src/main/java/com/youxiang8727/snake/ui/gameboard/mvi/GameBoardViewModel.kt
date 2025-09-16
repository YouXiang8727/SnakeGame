package com.youxiang8727.snake.ui.gameboard.mvi

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.youxiang8727.snake.core.mvi.MviViewModel
import com.youxiang8727.snake.domain.model.Direction
import com.youxiang8727.snake.domain.model.GameDifficulty
import com.youxiang8727.snake.domain.usecase.EatFoodUseCase
import com.youxiang8727.snake.domain.usecase.MoveSnakeUseCase
import com.youxiang8727.snake.domain.usecase.NewFoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameBoardViewModel @Inject constructor(
    private val moveSnakeUseCase: MoveSnakeUseCase,
    private val eatFoodUseCase: EatFoodUseCase,
    private val newFoodUseCase: NewFoodUseCase
): MviViewModel<GameBoardUiState, GameBoardUiEvent>(
    initialState = GameBoardUiState(),
    reducer = GameBoardReducer()
) {
    init {
        Log.d("David", "GameBoardViewModel($this) init")
    }

    private var snakeGameLoopJob: Job? = null

    fun selectGameDifficulty(difficulty: GameDifficulty) {
        dispatch(
            GameBoardUiEvent.SelectDifficulty(
                difficulty = difficulty
            )
        )
    }

    fun setGameBoardSize(size: Int) {
        dispatch(
            GameBoardUiEvent.SetBoardSize(
                size = size
            )
        )
    }

    fun newGame() {
        dispatch(
            GameBoardUiEvent.NewGame
        )
    }

    fun startGame() {
        dispatch(
            GameBoardUiEvent.StartGame
        )

        snakeGameLoopJob = snakeGameLoopJob()
    }

    fun changeDirection(direction: Direction) {
        dispatch(
            GameBoardUiEvent.ChangeDirection(
                direction
            )
        )
    }

    private fun snakeGameLoopJob(): Job {
        val handler = CoroutineExceptionHandler { _, exception ->
            dispatch(
                GameBoardUiEvent.GameOver
            )
        }
        return viewModelScope.launch(handler) {
            while (isActive) {
                val head = Pair(
                    state.value.snake.body.first().position.first + state.value.direction.addX,
                    state.value.snake.body.first().position.second + state.value.direction.addY
                )

                if (head == state.value.food) {
                    dispatch(
                        GameBoardUiEvent.MoveSnake(
                            snake = eatFoodUseCase(
                                snake = state.value.snake,
                                direction = state.value.direction,
                                boardSize = state.value.boardSize
                            )
                        )
                    )

                    dispatch(
                        GameBoardUiEvent.NewFood(
                            newFoodUseCase(
                                board = state.value.cells,
                                snake = state.value.snake)
                        )
                    )
                } else {
                    dispatch(
                        GameBoardUiEvent.MoveSnake(
                            snake = moveSnakeUseCase(
                                snake = state.value.snake,
                                direction = state.value.direction,
                                boardSize = state.value.boardSize
                            )
                        )
                    )
                }

                delay(state.value.difficulty.speed.toLong())
            }
        }
    }
}