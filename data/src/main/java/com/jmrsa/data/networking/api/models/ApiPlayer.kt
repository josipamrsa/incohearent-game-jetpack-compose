package com.jmrsa.data.networking.api.models

import com.jmrsa.domain.models.Player
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

@Serializable
data class ApiPlayer(
    val username: String,
    val color: String
)

fun String.decodeJsonToApiPlayer() =
    Json.decodeFromString<ApiPlayer>(this)

fun ApiPlayer.encodeApiPlayerToJson() =
    Json.encodeToJsonElement(this).toString()