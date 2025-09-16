package com.youxiang8727.snake.ui.gameboard

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.youxiang8727.snake.domain.model.SnakeBody
import kotlinx.coroutines.launch

@Composable
fun GameBoard(
    snake: List<SnakeBody>,
    food: Pair<Int, Int>,
    boardSize: Int,
    speed: Int
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .border(1.dp, Color.Black)
    ) {
        val cellSize = maxWidth / boardSize

        FoodCell(food, cellSize)

        snake.forEach { body ->
            SnakeCell(body, cellSize, speed)
        }
    }
}

@Composable
fun SnakeCell(body: SnakeBody, cellSize: Dp, speed: Int) {
    val animX = remember { Animatable(body.position.first.toFloat()) }
    val animY = remember { Animatable(body.position.second.toFloat()) }

    LaunchedEffect(body.position) {
        launch {
            animX.animateTo(body.position.first.toFloat(), tween(speed, easing = LinearEasing))
        }
        launch {
            animY.animateTo(body.position.second.toFloat(), tween(speed, easing = LinearEasing))
        }
    }

    Box(
        modifier = Modifier
            .size(cellSize)
            .offset {
                IntOffset(
                    (animX.value * cellSize.toPx()).toInt(),
                    (animY.value * cellSize.toPx()).toInt()
                )
            }
            .background(Color.Blue)
    )
}

@Composable
fun FoodCell(food: Pair<Int, Int>, cellSize: Dp) {
    Box(
        modifier = Modifier
            .size(cellSize)
            .offset {
                IntOffset(
                    (food.first * cellSize.toPx()).toInt(),
                    (food.second * cellSize.toPx()).toInt()
                )
            }
            .background(Color.Red)
    )
}
