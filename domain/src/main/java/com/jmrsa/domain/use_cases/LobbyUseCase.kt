package com.jmrsa.domain.use_cases

import com.jmrsa.domain.models.ActionData
import com.jmrsa.domain.models.Player
import kotlinx.coroutines.flow.Flow

interface LobbyUseCase {
    suspend fun observeLobbyMessages(): Flow<ActionData>
    suspend fun logPlayer(player: Player)
}