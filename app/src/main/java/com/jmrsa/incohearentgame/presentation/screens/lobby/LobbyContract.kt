package com.jmrsa.incohearentgame.presentation.screens.lobby

import androidx.compose.ui.graphics.Color
import com.jmrsa.incohearentgame.core.base.BaseContract
import com.jmrsa.incohearentgame.presentation.models.AppPlayer

interface LobbyContract:
    BaseContract<LobbyContract.State, LobbyContract.Event, LobbyContract.Effect> {

    data class State(
        val player: AppPlayer = AppPlayer(),
        val players: List<String> = emptyList(),
        val playerColors: List<Color> = emptyList(),
        val lobbyName: String = "",
        val lobbyNotifications: List<String> = emptyList()
    )

    sealed interface Event

    sealed interface Effect
}