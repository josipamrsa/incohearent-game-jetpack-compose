package com.jmrsa.data.networking

import com.jmrsa.data.networking.NetworkContract.BASE_SOCKET_URL
import com.jmrsa.data.networking.NetworkContract.LOBBY_SOCKET_URL
import com.jmrsa.data.networking.api.RealtimeClient
import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.webSocketSession
import io.ktor.client.request.url
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.WebSocketSession
import io.ktor.http.cio.websocket.close
import io.ktor.http.cio.websocket.readText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import timber.log.Timber

class RealtimeClientImpl(
    private val client: HttpClient
) : RealtimeClient {
    private var session: WebSocketSession? = null

    override fun connect(): Flow<String> {
        return flow {
            try {
                session = client.webSocketSession {
                    //url("ws://192.168.178.42:8080/lobby")
                    url(LOBBY_SOCKET_URL)
                }

                session?.incoming?.consumeAsFlow()
                    ?.filterIsInstance<Frame.Text>()
                    ?.map { it.readText() }
                    ?.collect { msg -> emit(msg) }
            } catch (e: Exception) { Timber.e(e.message) }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun sendAction(action: String) {
        session?.outgoing?.send(
            Frame.Text("Testtttt")
        )
    }

    override suspend fun close() {
        session?.close()
        session = null
    }
}