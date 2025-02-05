package com.jmrsa.data.networking.api.realtime_client

import com.jmrsa.data.networking.api.realtime_client.model.ApiActionData
import kotlinx.coroutines.flow.SharedFlow

interface RealtimeClient {
    val sessionFlow: SharedFlow<ApiActionData>
    suspend fun connect()
    suspend fun sendAction(action: String, data: String)
    suspend fun close()
}