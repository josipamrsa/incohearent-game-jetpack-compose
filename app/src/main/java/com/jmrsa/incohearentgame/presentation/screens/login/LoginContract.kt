package com.jmrsa.incohearentgame.presentation.screens.login

import com.jmrsa.incohearentgame.base.BaseContract

interface LoginContract: BaseContract<LoginContract.State, LoginContract.Event, LoginContract.Effect> {
    data object State
    sealed interface Event
    sealed interface Effect
}