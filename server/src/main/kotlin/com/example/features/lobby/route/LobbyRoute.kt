package com.example.features.lobby.route

import com.example.base.listenForIncomingFrames
import com.example.sessions.SessionManager
import com.jmrsa.protocol.ClientMessage
import com.jmrsa.protocol.Protocol
import com.jmrsa.protocol.ServerMessage
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import kotlinx.coroutines.launch

fun Route.lobbySocket(sessionManager: SessionManager) {
    webSocket(Protocol.LOBBY_PATH) {
        sessionManager.addSession(this)
        try {
            listenForIncomingFrames(incoming) { message ->
                launch {
                    when (message) {
                        is ClientMessage.LogNewPlayer -> sessionManager.broadcastMessage(
                            ServerMessage.NewPlayerJoined(message.player)
                        )

                        ClientMessage.BeginGame -> { /* TODO */ }
                    }
                }
            }
        } finally {
            sessionManager.removeSession(this)
        }
    }
}
