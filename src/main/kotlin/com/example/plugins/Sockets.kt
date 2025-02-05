package com.example.plugins

import com.example.plugins.helpers.ActionData
import com.example.plugins.helpers.decodeActionDataFromJson
import com.example.routes.lobbySocket
import io.ktor.serialization.kotlinx.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import player.Player
import player.encodePlayerToJson
import kotlin.time.Duration.Companion.seconds

fun Application.configureSockets() {
    install(WebSockets) {
        contentConverter = KotlinxWebsocketSerializationConverter(Json)
        pingPeriod = 15.seconds
        timeout = 15.seconds
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }
    routing {
        val sessions = mutableSetOf<WebSocketSession>()

        lobbySocket(sessions)

        /*webSocket("/lobby") {
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
                                    //val player = Player(message.data, "#CCDCEB")
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
            //close(CloseReason(CloseReason.Codes.NORMAL, "All done"))
        }*/
    }
}
