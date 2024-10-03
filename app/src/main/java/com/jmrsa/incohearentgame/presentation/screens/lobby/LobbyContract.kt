package com.jmrsa.incohearentgame.presentation.screens.lobby

import com.jmrsa.incohearentgame.core.base.BaseContract

interface LobbyContract:
    BaseContract<LobbyContract.State, LobbyContract.Event, LobbyContract.Effect> {
    data object State

    sealed interface Event

    sealed interface Effect
}