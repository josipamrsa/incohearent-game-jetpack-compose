package com.example

import com.jmrsa.protocol.ClientMessage
import com.jmrsa.protocol.PlayerDto
import com.jmrsa.protocol.Protocol
import com.jmrsa.protocol.ServerMessage
import com.jmrsa.protocol.decodeServerMessage
import com.jmrsa.protocol.encode
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.server.testing.testApplication
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlin.test.Test
import kotlin.test.assertEquals

class LobbySocketTest {

    @Test
    fun newPlayerIsBroadcastBack() = testApplication {
        application { module() }
        val client = createClient { install(WebSockets) }
        val player = PlayerDto(username = "Ana", color = "#FF7F50")

        client.webSocket(Protocol.LOBBY_PATH) {
            send(Frame.Text(ClientMessage.LogNewPlayer(player).encode()))

            val reply = incoming.receive() as Frame.Text
            assertEquals(ServerMessage.NewPlayerJoined(player), decodeServerMessage(reply.readText()))
        }
    }
}
