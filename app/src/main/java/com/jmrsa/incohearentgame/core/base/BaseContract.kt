package com.jmrsa.incohearentgame.core.base

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface BaseContract<State, Event, Effect> {
    val state : StateFlow<State>
    val effect: SharedFlow<Effect>
    fun onEvent(event: Event)
}