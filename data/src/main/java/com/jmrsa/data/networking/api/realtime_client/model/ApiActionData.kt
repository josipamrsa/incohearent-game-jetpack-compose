package com.jmrsa.data.networking.api.realtime_client.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

@Serializable
data class ApiActionData (
    val action: String,
    val data: String
)

fun String.decodeJsonToApiActionData() =
    Json.decodeFromString<ApiActionData>(this)

fun ApiActionData.encodeApiActionDataToJson() =
    Json.encodeToJsonElement(this).toString()