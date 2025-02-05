package com.example.routes

import com.example.plugins.helpers.ActionData
import com.example.plugins.helpers.decodeActionDataFromJson
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

fun Route.lobbySocket(sessions: MutableSet<WebSocketSession>) {
    webSocket("/lobby") {
        sessions.add(this)
        for (frame in incoming) {
            println("RECEIVED >> $frame")
            when (frame) {
                is Frame.Text -> {
                    val receivedMessage = frame.readText()

                    try {
                        val message = receivedMessage.decodeActionDataFromJson()

                        when (message.action) {
                            "GREET_USER" -> {
                                println("Data received: >> " + message.data)
                                sessions.forEach { session ->
                                    val data = ActionData("NEW_PLAYER_JOINED", message.data)
                                    session.send(Frame.Text(Json.encodeToJsonElement(data).toString()))
                                }
                            }

                            else -> {}
                        }

                    } catch (e: Exception) {
                        println("There's been an error: >> " + e.message)
                    }
                }

                else -> {}
            }
        }
    }
}