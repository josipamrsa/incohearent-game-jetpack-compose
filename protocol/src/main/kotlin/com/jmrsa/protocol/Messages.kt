package com.jmrsa.protocol

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Wire protocol between the Android client and the server, shared by both sides.
 *
 * Every WebSocket text frame is one JSON object whose `type` field names the message, e.g.
 * `{"type":"LOG_NEW_PLAYER","player":{"username":"Ana","color":"#FF7F50"}}`.
 * Changing a `@SerialName` or a field changes the wire format — both sides must be updated together.
 */
object Protocol {
    const val LOBBY_PATH = "/lobby"
}

@Serializable
data class PlayerDto(
    val username: String,
    val color: String
)

/** Messages sent from the client to the server. */
@Serializable
sealed interface ClientMessage {

    @Serializable
    @SerialName("LOG_NEW_PLAYER")
    data class LogNewPlayer(val player: PlayerDto) : ClientMessage

    @Serializable
    @SerialName("BEGIN_GAME")
    data object BeginGame : ClientMessage
}

/** Messages sent from the server to clients. */
@Serializable
sealed interface ServerMessage {

    @Serializable
    @SerialName("NEW_PLAYER_JOINED")
    data class NewPlayerJoined(val player: PlayerDto) : ServerMessage
}
