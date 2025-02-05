package com.jmrsa.domain.repositories

import com.jmrsa.domain.models.ActionData
import com.jmrsa.domain.models.Player
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface PlayerRepository {
    fun observeSessionFlow() : Flow<ActionData>
    suspend fun logNewPlayer(player: Player)
}