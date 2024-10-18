package com.jmrsa.incohearentgame.presentation.screens.lobby

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import com.jmrsa.incohearentgame.core.base.composeViewModel
import com.jmrsa.incohearentgame.core.base.utils.collectInLaunchedEffect
import com.jmrsa.incohearentgame.core.navigation.Destination
import com.jmrsa.incohearentgame.core.navigation.typeComposable
import com.jmrsa.incohearentgame.presentation.screens.lobby.components.LobbyInfoView
import com.jmrsa.incohearentgame.presentation.screens.lobby.components.PlayerGridView
import com.jmrsa.incohearentgame.presentation.shared.base.ScaffoldBaseScreen
import com.jmrsa.incohearentgame.presentation.shared.topbars.BasicTopBar
import kotlinx.serialization.Serializable

@Serializable
object LobbyDestination : Destination

fun NavGraphBuilder.lobbyScreen() {
    typeComposable<LobbyDestination> {
        val viewModel = composeViewModel<LobbyViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        val onEvent = viewModel::onEvent

        viewModel.effect.collectInLaunchedEffect { }

        LobbyScreen(
            state = state,
            event = onEvent
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LobbyScreen(
    state: LobbyContract.State,
    event: (LobbyContract.Event) -> Unit
) {
    ScaffoldBaseScreen(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BasicTopBar(
                title = { Text(text = "Lobby") }
            )
        },
        bottomBar = {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 10.dp,
                        horizontal = 15.dp
                    )
            ) {
                Text(text = "Begin game")
            }
        },
        isScrollEnabled = false
    ) {
        PlayerGridView(
            playerList = state.players,
            playerColors = state.playerColors
        )

        LobbyInfoView(state.lobbyNotifications)
    }
}

@Preview
@Composable
fun PreviewLobbyScreen() {
    LobbyScreen(
        state = LobbyContract.State(),
        event = {}
    )
}