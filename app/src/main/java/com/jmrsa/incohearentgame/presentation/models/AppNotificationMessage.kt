package com.jmrsa.incohearentgame.presentation.models

import androidx.annotation.StringRes

data class AppNotificationMessage(
    @StringRes val resource: Int,
    val args: List<String> = listOf()
)