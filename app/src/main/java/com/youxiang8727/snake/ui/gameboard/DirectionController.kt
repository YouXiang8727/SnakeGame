package com.youxiang8727.snake.ui.gameboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.hilt.navigation.compose.hiltViewModel
import com.youxiang8727.snake.domain.model.Direction
import com.youxiang8727.snake.domain.model.GameState
import com.youxiang8727.snake.ui.gameboard.mvi.GameBoardViewModel

@Composable
fun DirectionController(
    viewModel: GameBoardViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    Box(
        modifier = Modifier.fillMaxHeight()
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        val directions = Direction.entries
        val alignments = listOf(
            Alignment.CenterStart,
            Alignment.TopCenter,
            Alignment.CenterEnd,
            Alignment.BottomCenter
        )

        directions.zip(alignments).forEach { (direction, alignment) ->
            Button(
                onClick = {
                    viewModel.changeDirection(direction)
                },
                enabled = state.value.gameState == GameState.START,
                modifier = Modifier.fillMaxHeight(.3f)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .align(alignment)
            ) {
            }
        }
    }
}