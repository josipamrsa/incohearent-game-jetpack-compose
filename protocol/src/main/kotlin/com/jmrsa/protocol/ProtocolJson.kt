package com.jmrsa.protocol

import kotlinx.serialization.json.Json

/** The single JSON configuration used for the wire format on both sides. */
val ProtocolJson = Json {
    classDiscriminator = "type"
    ignoreUnknownKeys = true
    encodeDefaults = true
}

fun ClientMessage.encode(): String = ProtocolJson.encodeToString(ClientMessage.serializer(), this)

fun ServerMessage.encode(): String = ProtocolJson.encodeToString(ServerMessage.serializer(), this)

fun decodeClientMessage(text: String): ClientMessage =
    ProtocolJson.decodeFromString(ClientMessage.serializer(), text)

fun decodeServerMessage(text: String): ServerMessage =
    ProtocolJson.decodeFromString(ServerMessage.serializer(), text)
