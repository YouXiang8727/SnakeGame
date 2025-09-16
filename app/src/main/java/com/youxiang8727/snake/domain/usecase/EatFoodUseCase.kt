package com.youxiang8727.snake.domain.usecase

import com.youxiang8727.snake.domain.model.Direction
import com.youxiang8727.snake.domain.model.Snake
import com.youxiang8727.snake.domain.model.move
import javax.inject.Inject

class EatFoodUseCase @Inject constructor() {
    operator fun invoke(
        snake: Snake,
        direction: Direction,
        boardSize: Int
    ):Snake {
        return snake.move(direction, boardSize, true)
    }
}