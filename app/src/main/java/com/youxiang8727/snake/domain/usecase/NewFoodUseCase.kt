package com.youxiang8727.snake.domain.usecase

import com.youxiang8727.snake.domain.model.Snake
import javax.inject.Inject

class NewFoodUseCase @Inject constructor() {
    operator fun invoke(
        board: List<Pair<Int, Int>>,
        snake: Snake
    ): Pair<Int, Int> {
        return board.filterNot { pair ->
            snake.body.any { body ->
                body.position == pair
            }
        }.random()
    }
}