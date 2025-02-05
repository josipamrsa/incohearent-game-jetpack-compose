package com.jmrsa.data.networking.api.realtime_client

import com.jmrsa.data.networking.api.realtime_client.model.ApiActionData
import io.ktor.http.cio.websocket.WebSocketSession
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface RealtimeClient {
    val sessionFlow: SharedFlow<ApiActionData>
    suspend fun connect()
    suspend fun sendAction(action: String, data: String)
    suspend fun close()
}