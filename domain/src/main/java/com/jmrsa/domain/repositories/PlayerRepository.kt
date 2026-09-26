package com.jmrsa.domain.repositories

import com.jmrsa.domain.models.LobbyEvent
import com.jmrsa.domain.models.Player
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {
    fun observeLobbyEvents() : Flow<LobbyEvent>
    suspend fun logNewPlayer(player: Player)
}
