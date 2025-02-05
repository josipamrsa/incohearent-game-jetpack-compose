package com.jmrsa.data.networking.api.converters

import com.jmrsa.data.networking.api.models.ApiPlayer
import com.jmrsa.domain.models.Player

fun ApiPlayer.toPlayer() = Player(
    username = username,
    color = color
)

fun Player.toApiPlayer() = ApiPlayer(
    username = username,
    color = color
)