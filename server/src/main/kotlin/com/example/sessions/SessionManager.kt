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
        val deadSessions = mutableListOf<WebSocketSession>()
        _sessions.forEach { session ->
            try {
                session.send(Frame.Text(message.encode()))
            } catch (e: Exception) {
                println("Failed to send to session, dropping it: >> ${e.message}")
                deadSessions.add(session)
            }
        }
        deadSessions.forEach(::removeSession)
    }
}
