package com.jmrsa.incohearentgame.presentation.models

import com.jmrsa.data.networking.api.models.ApiPlayer
import com.jmrsa.domain.models.Player

data class AppPlayer(
    val username: String = "",
    val color: String = ""
)

fun AppPlayer.toPlayer() = Player(
    username = username,
    color = color
)

fun Player.toAppPlayer() = AppPlayer(
    username = username,
    color = color
)

fun ApiPlayer.toAppPlayer() = AppPlayer(
    username = username,
    color = color
)