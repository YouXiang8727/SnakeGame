package com.youxiang8727.snake.domain.model

enum class GameDifficulty(val speed: Int) {
    EASY(speed = 300),
    MEDIUM(speed = 200),
    HARD(speed = 100)
}