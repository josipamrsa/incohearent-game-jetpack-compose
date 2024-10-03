package com.jmrsa.incohearentgame.presentation.screens.login

import com.jmrsa.incohearentgame.core.base.BaseContract

interface LoginContract:
    BaseContract<LoginContract.State, LoginContract.Event, LoginContract.Effect> {
    data class State(
        val username: String = ""
    ) {
        val canLogin: Boolean
            get() = username.length in 1..20
    }

    sealed interface Event {
        data class OnUsernameChanged(val name: String): Event
        data object OnContinueLogin : Event
    }

    sealed interface Effect {
        data object ContinueLogin: Effect
    }
}