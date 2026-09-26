package com.jmrsa.domain.use_cases

import com.jmrsa.domain.models.LobbyEvent
import com.jmrsa.domain.models.Player
import kotlinx.coroutines.flow.Flow

interface LobbyUseCase {
    suspend fun observeLobbyMessages(): Flow<LobbyEvent>
    suspend fun logPlayer(player: Player)
}
