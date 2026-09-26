package com.example.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

@Serializable
data class ActionData(
    val action: String,
    val data: String
)

fun String.decodeActionDataFromJson() =
    Json.decodeFromString<ActionData>(this)

fun ActionData.encodeActionDataToJson() =
    Json.encodeToJsonElement(this).toString()