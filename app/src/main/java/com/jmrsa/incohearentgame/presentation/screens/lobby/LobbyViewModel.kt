package com.jmrsa.incohearentgame.presentation.screens.lobby

import androidx.lifecycle.viewModelScope
import com.jmrsa.data.networking.api.RealtimeClient
import com.jmrsa.incohearentgame.core.base.BaseViewModel
import com.jmrsa.incohearentgame.presentation.utils.DUMMY_PLAYERS
import com.jmrsa.incohearentgame.ui.theme.LightBlue
import com.jmrsa.incohearentgame.ui.theme.LightCoral
import com.jmrsa.incohearentgame.ui.theme.LightOrange
import com.jmrsa.incohearentgame.ui.theme.MuddyYellow
import com.jmrsa.incohearentgame.ui.theme.SeaGreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LobbyViewModel @Inject constructor(
    private val client: RealtimeClient
) : BaseViewModel(), LobbyContract {
    private val mutableState = MutableStateFlow(LobbyContract.State(
        players = PLAYERS,
        playerColors = PLAYER_COLORS
    ))

    private val _isConnecting = MutableStateFlow(false)
    val isConnect = _isConnecting.asStateFlow()

    override val state = mutableState.asStateFlow()

    private val mutableEffect = MutableSharedFlow<LobbyContract.Effect>()
    override val effect = mutableEffect.asSharedFlow()

    init {
        viewModelScope.launch {
            client.connect().collect { msg ->
                msg
            }
        }
    }

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