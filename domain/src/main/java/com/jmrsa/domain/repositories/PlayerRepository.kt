package com.jmrsa.domain.repositories

import com.jmrsa.domain.models.ActionData
import com.jmrsa.domain.models.Player
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {
    fun observeSessionFlow() : Flow<ActionData>
    suspend fun logNewPlayer(player: Player)
}