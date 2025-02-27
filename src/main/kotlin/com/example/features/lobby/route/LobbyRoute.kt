package com.example.features.lobby.route

import com.example.base.listenForIncomingFrames
import com.example.models.ActionData
import com.example.plugins.helpers.EventMessages
import com.example.sessions.SessionManager
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import kotlinx.coroutines.launch

fun Route.lobbySocket(sessionManager: SessionManager) {
    webSocket("/lobby") {
        sessionManager.addSession(this)
        listenForIncomingFrames(incoming) { message ->
            launch {
                when (message.action) {
                    EventMessages.LOG_NEW_PLAYER -> sessionManager.broadcastMessage(
                        ActionData(
                            EventMessages.NEW_PLAYER_JOINED,
                            message.data
                        )
                    )

                    else -> { /* TODO */ }
                }
            }
        }
    }
}

