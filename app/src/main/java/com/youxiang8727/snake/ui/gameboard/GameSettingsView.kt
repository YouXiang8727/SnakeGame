package com.youxiang8727.snake.ui.gameboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.youxiang8727.snake.domain.model.GameDifficulty
import com.youxiang8727.snake.domain.model.GameState
import com.youxiang8727.snake.ui.gameboard.mvi.GameBoardViewModel

@Composable
fun GameSettingsView(
    viewModel: GameBoardViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf(15, 20, 25).forEach { size ->
                FilterChip(
                    selected = state.value.boardSize == size,
                    onClick = {
                        viewModel.setGameBoardSize(size)
                    },
                    label = {
                        Text("$size * $size")
                    },
                    enabled = state.value.gameState == GameState.INIT
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            GameDifficulty.entries.forEach { gameDifficulty ->
                FilterChip(
                    selected = state.value.difficulty == gameDifficulty,
                    onClick = {
                        viewModel.selectGameDifficulty(gameDifficulty)
                    },
                    label = {
                        Text(gameDifficulty.name)
                    },
                    enabled = state.value.gameState == GameState.INIT
                )
            }
        }
    }
}