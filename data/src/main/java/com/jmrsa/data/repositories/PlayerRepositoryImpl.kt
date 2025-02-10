package com.jmrsa.data.repositories

import android.annotation.SuppressLint
import com.jmrsa.data.networking.api.converters.toApiPlayer
import com.jmrsa.data.networking.api.models.encodeApiPlayerToJson
import com.jmrsa.data.networking.api.realtime_client.RealtimeClient
import com.jmrsa.data.networking.api.realtime_client.converter.toActionData
import com.jmrsa.data.networking.api.realtime_client.model.SendEventMessages
import com.jmrsa.domain.models.ActionData
import com.jmrsa.domain.models.Player
import com.jmrsa.domain.repositories.PlayerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class PlayerRepositoryImpl(
    private val client: RealtimeClient
): PlayerRepository {
    @SuppressLint("LogNotTimber")
    override fun observeSessionFlow(): Flow<ActionData> = client.sessionFlow.map { it.toActionData() }

    override suspend fun logNewPlayer(player: Player) {
        client.sendAction(SendEventMessages.LOG_NEW_PLAYER, player.toApiPlayer().encodeApiPlayerToJson())
    }
}