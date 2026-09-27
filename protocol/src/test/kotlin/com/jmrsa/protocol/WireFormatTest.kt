package com.jmrsa.protocol

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Pins the exact JSON of every message. If one of these fails, the wire format changed:
 * make sure that was intended, since older app versions talking to a newer server will break.
 */
class WireFormatTest {

    private val player = PlayerDto(username = "Ana", color = "#FF7F50")

    @Test
    fun logNewPlayer() = assertWire(
        ClientMessage.LogNewPlayer(player),
        """{"type":"LOG_NEW_PLAYER","player":{"username":"Ana","color":"#FF7F50"}}"""
    )

    @Test
    fun beginGame() = assertWire(
        ClientMessage.BeginGame,
        """{"type":"BEGIN_GAME"}"""
    )

    @Test
    fun joinedLobby() = assertWire(
        ServerMessage.JoinedLobby(player),
        """{"type":"JOINED_LOBBY","player":{"username":"Ana","color":"#FF7F50"}}"""
    )

    @Test
    fun newPlayerJoined() = assertWire(
        ServerMessage.NewPlayerJoined(player),
        """{"type":"NEW_PLAYER_JOINED","player":{"username":"Ana","color":"#FF7F50"}}"""
    )

    @Test
    fun unknownFieldsAreIgnored() {
        val decoded = decodeServerMessage(
            """{"type":"NEW_PLAYER_JOINED","player":{"username":"Ana","color":"#FF7F50","extra":1},"extra":true}"""
        )
        assertEquals(ServerMessage.NewPlayerJoined(player), decoded)
    }

    private fun assertWire(message: ClientMessage, json: String) {
        assertEquals(parse(json), parse(message.encode()))
        assertEquals(message, decodeClientMessage(json))
    }

    private fun assertWire(message: ServerMessage, json: String) {
        assertEquals(parse(json), parse(message.encode()))
        assertEquals(message, decodeServerMessage(json))
    }

    private fun parse(json: String): JsonElement = Json.parseToJsonElement(json)
}
