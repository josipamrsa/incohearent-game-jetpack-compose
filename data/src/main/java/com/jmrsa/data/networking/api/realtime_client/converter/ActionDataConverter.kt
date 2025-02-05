package com.jmrsa.data.networking.api.realtime_client.converter

import com.jmrsa.data.networking.api.models.ApiPlayer
import com.jmrsa.data.networking.api.realtime_client.model.ApiActionData
import com.jmrsa.domain.models.ActionData
import com.jmrsa.domain.models.Player

fun ApiActionData.toActionData() = ActionData(
    action = action,
    data = data
)

fun ActionData.toApiActionData() = ApiActionData(
    action = action,
    data = data
)