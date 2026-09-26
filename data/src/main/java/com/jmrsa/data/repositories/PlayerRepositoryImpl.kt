package com.jmrsa.data.repositories

import com.jmrsa.data.networking.api.converters.toLobbyEvent
import com.jmrsa.data.networking.api.converters.toPlayerDto
import com.jmrsa.data.networking.api.realtime_client.RealtimeClient
import com.jmrsa.domain.models.LobbyEvent
import com.jmrsa.domain.models.Player
import com.jmrsa.domain.repositories.PlayerRepository
import com.jmrsa.protocol.ClientMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class PlayerRepositoryImpl(
    private val client: RealtimeClient
): PlayerRepository {
    override fun observeLobbyEvents(): Flow<LobbyEvent> = client.sessionFlow.map { it.toLobbyEvent() }

    override suspend fun logNewPlayer(player: Player) {
        client.send(ClientMessage.LogNewPlayer(player.toPlayerDto()))
    }
}
