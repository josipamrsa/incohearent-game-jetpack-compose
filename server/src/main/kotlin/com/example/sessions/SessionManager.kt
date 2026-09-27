package com.example.sessions

import com.jmrsa.protocol.ServerMessage
import com.jmrsa.protocol.encode
import io.ktor.websocket.*
import java.util.concurrent.ConcurrentHashMap

object SessionManager {
    // Mutated from every connection's coroutine; a concurrent set also lets broadcasts iterate while sessions come and go.
    private val _sessions: MutableSet<WebSocketSession> = ConcurrentHashMap.newKeySet()

    fun addSession(session: WebSocketSession) {
        _sessions.add(session)
    }

    fun removeSession(session: WebSocketSession) {
        _sessions.remove(session)
    }

    suspend fun sendMessage(session: WebSocketSession, message: ServerMessage) {
        println("Sending: >> $message")
        try {
            session.send(Frame.Text(message.encode()))
        } catch (e: Exception) {
            println("Failed to send to session, dropping it: >> ${e.message}")
            removeSession(session)
        }
    }

    suspend fun broadcastMessage(message: ServerMessage, except: WebSocketSession? = null) {
        println("Broadcasting: >> $message")
        val deadSessions = mutableListOf<WebSocketSession>()
        _sessions.filter { it != except }.forEach { session ->
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
