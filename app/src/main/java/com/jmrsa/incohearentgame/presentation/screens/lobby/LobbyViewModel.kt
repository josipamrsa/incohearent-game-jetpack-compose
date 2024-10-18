package com.jmrsa.incohearentgame.presentation.screens.lobby

import androidx.compose.ui.graphics.Color
import com.jmrsa.incohearentgame.core.base.BaseViewModel
import com.jmrsa.incohearentgame.presentation.utils.DUMMY_PLAYERS
import com.jmrsa.incohearentgame.ui.theme.LightBlue
import com.jmrsa.incohearentgame.ui.theme.LightCoral
import com.jmrsa.incohearentgame.ui.theme.LightOrange
import com.jmrsa.incohearentgame.ui.theme.MuddyYellow
import com.jmrsa.incohearentgame.ui.theme.SeaGreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LobbyViewModel @Inject constructor() : BaseViewModel(), LobbyContract {
    private val mutableState = MutableStateFlow(LobbyContract.State(
        players = PLAYERS,
        playerColors = PLAYER_COLORS
    ))
    override val state = mutableState.asStateFlow()

    private val mutableEffect = MutableSharedFlow<LobbyContract.Effect>()
    override val effect = mutableEffect.asSharedFlow()

    override fun onEvent(event: LobbyContract.Event) {

    }

    companion object {
        val PLAYER_COLORS = listOf(
            SeaGreen,
            LightBlue,
            LightCoral,
            LightOrange,
            MuddyYellow
        )
        val PLAYERS = DUMMY_PLAYERS
    }
}