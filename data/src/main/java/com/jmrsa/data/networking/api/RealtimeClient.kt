package com.jmrsa.data.networking.api

import kotlinx.coroutines.flow.Flow

interface RealtimeClient {
    fun connect(): Flow<String>
    suspend fun sendAction(action: String)
    suspend fun close()
}