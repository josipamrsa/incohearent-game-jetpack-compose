package com.jmrsa.incohearentgame.presentation.screens.lobby

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.jmrsa.data.networking.api.models.decodeJsonToApiPlayer
import com.jmrsa.domain.use_cases.LobbyUseCase
import com.jmrsa.incohearentgame.R
import com.jmrsa.incohearentgame.core.base.BaseViewModel
import com.jmrsa.incohearentgame.core.network.ReceiveEventMessages
import com.jmrsa.incohearentgame.presentation.models.AppNotificationMessage
import com.jmrsa.incohearentgame.presentation.models.AppPlayer
import com.jmrsa.incohearentgame.presentation.models.toAppPlayer
import com.jmrsa.incohearentgame.presentation.models.toPlayer
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
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@SuppressLint("LogNotTimber")
@HiltViewModel
class LobbyViewModel @Inject constructor(
    private val lobbyUseCase: LobbyUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(), LobbyContract {

    private val args: LobbyDestination = savedStateHandle.toRoute()

    private val mutableState = MutableStateFlow(
        LobbyContract.State(
            players = PLAYERS,
            playerColors = PLAYER_COLORS
        )
    )

    override val state = mutableState.asStateFlow()

    private val mutableEffect = MutableSharedFlow<LobbyContract.Effect>()
    override val effect = mutableEffect.asSharedFlow()

    private fun handlePlayerUpdates(update: String) {
        val player = update.decodeJsonToApiPlayer().toAppPlayer()
        val updatedList =
            mutableState.value.players.toMutableList().plus(player.username)
        val lobbyMessages = mutableState.value.lobbyNotifications.toMutableList().plus(
            AppNotificationMessage(R.string.inc_notif_new_player_joined, listOf(player.username))
        )

        mutableState.update {
            it.copy(
                players = updatedList,
                lobbyNotifications = lobbyMessages
            )
        }
    }

    init {
        val player = AppPlayer(args.username)
        mutableState.update { it.copy(player = player) }

        launchInScope {
            lobbyUseCase.observeLobbyMessages().collect { update ->
                when (update.action) {
                    ReceiveEventMessages.NEW_PLAYER_JOINED -> handlePlayerUpdates(update.data)

                    else -> {
                        Log.d("LobbyViewModel", "${update.action} >> ${update.data}")
                    }
                }
            }

        }

        launchInScope {
            lobbyUseCase.logPlayer(player = player.toPlayer())
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
        val PLAYERS = emptyList<String>()
    }
}