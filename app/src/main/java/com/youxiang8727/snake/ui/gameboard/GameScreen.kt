package com.youxiang8727.snake.ui.gameboard

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.youxiang8727.snake.ui.gameboard.mvi.GameBoardViewModel

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    viewModel: GameBoardViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        GameSettingsView()

        Text("Score: ${state.value.score}")

        Box(
            modifier = Modifier.fillMaxWidth()
                .aspectRatio(1f)
        ) {
            GameBoard(
                snake = state.value.snake.body,
                food = state.value.food,
                boardSize = state.value.boardSize,
                speed = state.value.difficulty.speed
            )

            GameStateMask()
        }

        Box(
            modifier = Modifier.fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            DirectionController()
        }
    }
}
