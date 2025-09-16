package com.youxiang8727.snake.domain.model

enum class Direction(val addX: Int,val addY: Int) {
    LEFT(addX = -1, addY = 0),
    TOP(addX = 0, addY = -1),
    RIGHT(addX = 1, addY = 0),
    BOTTOM(addX = 0, addY = 1)
}