package com.jmrsa.incohearentgame.presentation.screens.splash

import com.jmrsa.incohearentgame.core.base.BaseContract

interface SplashContract:
    BaseContract<SplashContract.State, SplashContract.Event, SplashContract.Effect> {
    data object State

    sealed interface Event

    sealed interface Effect
}


