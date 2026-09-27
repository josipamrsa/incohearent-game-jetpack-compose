package com.jmrsa.data.networking.api.converters

import com.jmrsa.domain.models.LobbyEvent
import com.jmrsa.protocol.ServerMessage

fun ServerMessage.toLobbyEvent(): LobbyEvent = when (this) {
    is ServerMessage.NewPlayerJoined -> LobbyEvent.PlayerJoined(player.toPlayer())
}
