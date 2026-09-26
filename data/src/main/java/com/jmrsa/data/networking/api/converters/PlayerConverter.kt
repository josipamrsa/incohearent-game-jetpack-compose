package com.jmrsa.data.networking.api.converters

import com.jmrsa.domain.models.Player
import com.jmrsa.protocol.PlayerDto

fun PlayerDto.toPlayer() = Player(
    username = username,
    color = color
)

fun Player.toPlayerDto() = PlayerDto(
    username = username,
    color = color
)
