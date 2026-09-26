package com.jmrsa.data.networking.api.realtime_client

import com.jmrsa.protocol.ClientMessage
import com.jmrsa.protocol.ServerMessage
import kotlinx.coroutines.flow.SharedFlow

interface RealtimeClient {
    val sessionFlow: SharedFlow<ServerMessage>
    suspend fun connect()
    suspend fun send(message: ClientMessage)
    suspend fun close()
}
