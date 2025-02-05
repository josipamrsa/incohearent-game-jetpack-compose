package com.jmrsa.incohearentgame.presentation.models

import com.jmrsa.data.networking.api.realtime_client.model.ApiActionData
import com.jmrsa.domain.models.ActionData

data class AppActionData(
    val action: String = "",
    val data: String = ""
)

fun AppActionData.toActionData() = ActionData(
    action = action,
    data = data
)

fun ActionData.toAppActionData() = AppActionData(
    action = action,
    data = data
)

fun ApiActionData.toAppActionData() = AppActionData(
    action = action,
    data = data
)