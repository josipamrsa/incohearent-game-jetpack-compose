package com.example.sessions

import com.jmrsa.protocol.ServerMessage
import com.jmrsa.protocol.encode
import io.ktor.websocket.*

object SessionManager {
    private val _sessions = mutableSetOf<WebSocketSession>()

    fun addSession(session: WebSocketSession) {
        _sessions.add(session)
    }

    fun removeSession(session: WebSocketSession) {
        _sessions.remove(session)
    }

    suspend fun broadcastMessage(message: ServerMessage) {
        println("Broadcasting: >> $message")
        _sessions.forEach { session ->
            session.send(Frame.Text(message.encode()))
        }
    }
}
