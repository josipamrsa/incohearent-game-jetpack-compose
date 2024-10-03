package com.jmrsa.incohearentgame.presentation.screens.lobby

import com.jmrsa.incohearentgame.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LobbyViewModel @Inject constructor() : BaseViewModel(), LobbyContract {
    private val mutableState = MutableStateFlow(LobbyContract.State)
    override val state = mutableState.asStateFlow()

    private val mutableEffect = MutableSharedFlow<LobbyContract.Effect>()
    override val effect = mutableEffect.asSharedFlow()

    override fun onEvent(event: LobbyContract.Event) {

    }
}