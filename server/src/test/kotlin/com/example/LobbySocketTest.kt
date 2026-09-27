package com.example

import com.jmrsa.protocol.ClientMessage
import com.jmrsa.protocol.PlayerDto
import com.jmrsa.protocol.Protocol
import com.jmrsa.protocol.ServerMessage
import com.jmrsa.protocol.decodeServerMessage
import com.jmrsa.protocol.encode
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.server.testing.testApplication
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class LobbySocketTest {

    private val ana = PlayerDto(username = "Ana", color = "#FF7F50")
    private val bruno = PlayerDto(username = "Bruno", color = "#2E8B57")

    @Test
    fun joinerGetsOwnJoinConfirmation() = testApplication {
        application { module() }
        val client = createClient { install(WebSockets) }

        client.webSocket(Protocol.LOBBY_PATH) {
            send(Frame.Text(ClientMessage.LogNewPlayer(ana).encode()))

            assertEquals(ServerMessage.JoinedLobby(ana), receiveMessage())
        }
    }

    @Test
    fun othersGetNewPlayerJoinedButJoinerDoesNot() = testApplication {
        application { module() }
        val client = createClient { install(WebSockets) }

        client.webSocket(Protocol.LOBBY_PATH) {
            val anaSession = this
            send(Frame.Text(ClientMessage.LogNewPlayer(ana).encode()))
            // Ana's own confirmation also proves her session is registered before Bruno joins.
            assertEquals(ServerMessage.JoinedLobby(ana), receiveMessage())

            client.webSocket(Protocol.LOBBY_PATH) {
                send(Frame.Text(ClientMessage.LogNewPlayer(bruno).encode()))

                assertEquals(ServerMessage.JoinedLobby(bruno), receiveMessage())
                assertEquals(ServerMessage.NewPlayerJoined(bruno), anaSession.receiveMessage())
                assertNull(withTimeoutOrNull(300) { incoming.receive() }, "Joiner must not get its own NEW_PLAYER_JOINED")
            }
        }
    }

    private suspend fun DefaultClientWebSocketSession.receiveMessage(): ServerMessage =
        decodeServerMessage((incoming.receive() as Frame.Text).readText())
}
