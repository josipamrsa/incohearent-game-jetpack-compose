package com.example.sessions

import com.example.models.ActionData
import io.ktor.websocket.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

object SessionManager {
    private val _sessions = mutableSetOf<WebSocketSession>()

    fun addSession(session: WebSocketSession) {
        _sessions.add(session)
    }

    fun removeSession(session: WebSocketSession) {
        _sessions.remove(session)
    }

    suspend fun broadcastMessage(actionData: ActionData) {
        println("Data received: >> " + actionData.data)
        _sessions.forEach { session ->
            session.send(Frame.Text(Json.encodeToJsonElement(actionData).toString()))
        }
    }
}