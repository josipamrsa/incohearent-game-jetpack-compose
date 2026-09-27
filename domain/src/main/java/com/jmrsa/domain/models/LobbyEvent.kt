package com.jmrsa.domain.models

sealed interface LobbyEvent {
    data class PlayerJoined(val player: Player) : LobbyEvent
}
