package com.jmrsa.data.networking.api.realtime_client

import android.annotation.SuppressLint
import android.util.Log
import com.jmrsa.data.networking.NetworkContract.LOBBY_SOCKET_URL
import com.jmrsa.protocol.ClientMessage
import com.jmrsa.protocol.ServerMessage
import com.jmrsa.protocol.decodeServerMessage
import com.jmrsa.protocol.encode
import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.webSocketSession
import io.ktor.client.request.url
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.WebSocketSession
import io.ktor.http.cio.websocket.close
import io.ktor.http.cio.websocket.readText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class RealtimeClientImpl(
    private val client: HttpClient
) : RealtimeClient {
    private var session: WebSocketSession? = null
    private var sessionJob: Job? = null
    private var isActive = true
    override val sessionFlow = MutableSharedFlow<ServerMessage>(replay = 1)

    init {
        startSession()
    }

    @SuppressLint("LogNotTimber")
    private fun startSession() {
        if (sessionJob?.isActive == true) return

        sessionJob = CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                try {
                    connect()
                } catch (e: Exception) {
                    Log.d(
                        "RealtimeClient",
                        "Exception occured >> ${e.cause} : ${e.message.orEmpty()}"
                    )
                }
                delay(5000)
            }
        }
    }

    @SuppressLint("LogNotTimber")
    override suspend fun connect() {
        try {
            session = client.webSocketSession { url(LOBBY_SOCKET_URL) }
            session?.incoming?.consumeAsFlow()
                ?.filterIsInstance<Frame.Text>()
                ?.map { it.readText() }
                ?.collect { msg ->
                    sessionFlow.emit(decodeServerMessage(msg))
                }
        } finally {
            Log.d("RealtimeClient", "Attempting to reconnect...")
        }
    }

    override suspend fun send(message: ClientMessage) {
        session?.outgoing?.send(
            Frame.Text(message.encode())
        )
    }

    @SuppressLint("LogNotTimber")
    override suspend fun close() {
        isActive = false
        session?.close()
        sessionJob?.cancel()
        session = null
        Log.d("RealtimeClient", "WebSocket closed manually")
    }
}