package player

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

@Serializable
data class Player(
    val username: String,
    val color: String
)

fun String.decodePlayerFromJson() =
    Json.decodeFromString<Player>(this)

fun Player.encodePlayerToJson() =
    Json.encodeToJsonElement(this).toString()
