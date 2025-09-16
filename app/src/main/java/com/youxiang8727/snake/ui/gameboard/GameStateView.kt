package com.youxiang8727.snake.ui.gameboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.youxiang8727.snake.domain.model.GameState
import com.youxiang8727.snake.ui.gameboard.mvi.GameBoardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameStateMask(
    viewModel: GameBoardViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()

    when (state.value.gameState) {
        GameState.START -> return

        GameState.INIT, GameState.PAUSE -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(Color.LightGray.copy(.5f)),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = {
                        viewModel.startGame()
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                    )
                }
            }
        }

        GameState.GAME_OVER -> {
            BasicAlertDialog(
                onDismissRequest = {},
                properties = DialogProperties(
                    dismissOnBackPress = false,
                    dismissOnClickOutside = false,
                    usePlatformDefaultWidth = true
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .aspectRatio(2f)
                        .background(Color.White)
                        .padding(8.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = "Game Over",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = "Score: ${state.value.score}",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Button(
                        onClick = {
                            viewModel.newGame()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("New Game")
                    }
                }
            }
        }
    }
}