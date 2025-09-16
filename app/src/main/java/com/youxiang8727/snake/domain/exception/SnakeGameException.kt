package com.youxiang8727.snake.domain.exception

sealed class SnakeGameException(message: String): RuntimeException(message) {
    class HitWallException: SnakeGameException("Snake hit the wall")

    class SelfCollectionException: SnakeGameException("Snake hit itself")
}