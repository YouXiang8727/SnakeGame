package com.youxiang8727.snake.domain.model

import com.youxiang8727.snake.domain.exception.SnakeGameException

data class Snake(
    val body: List<SnakeBody> = listOf(
        SnakeBody(
            position = Pair(0, 0),
            direction = Direction.RIGHT,
        )
    ),
)

data class SnakeBody(
    val position: Pair<Int, Int>,
    val direction: Direction
)

fun Snake.move(
    direction: Direction,
    boardSize: Int,
    eatFood: Boolean
): Snake {
    val x = this.body.first().position.first + direction.addX

    val y = this.body.first().position.second + direction.addY

    if (x < 0 || x >= boardSize || y < 0 || y >= boardSize) {
        throw SnakeGameException.HitWallException()
    }

    if (this.body.any { it.position == Pair(x, y) }) {
        throw SnakeGameException.SelfCollectionException()
    }

    return this.copy(
        body = listOf(
            SnakeBody(
                position = Pair(x, y),
                direction = direction,
            )
        ) + this.body.dropLast(
            if (eatFood) 0 else 1
        )
    )
}
