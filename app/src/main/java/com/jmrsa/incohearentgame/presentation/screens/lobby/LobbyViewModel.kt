package com.jmrsa.incohearentgame.presentation.screens.lobby

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.jmrsa.domain.models.LobbyEvent
import com.jmrsa.domain.use_cases.LobbyUseCase
import com.jmrsa.incohearentgame.R
import com.jmrsa.incohearentgame.core.base.BaseViewModel
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

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

    private fun handlePlayerUpdates(player: AppPlayer, isMe: Boolean = false) {
        val updatedList = mutableState.value.players.plus(
            LobbyContract.LobbyPlayer(username = player.username, isMe = isMe)
        )
        val notification = if (isMe) R.string.inc_notif_self_joined else R.string.inc_notif_new_player_joined
        val lobbyMessages = mutableState.value.lobbyNotifications.plus(
            AppNotificationMessage(notification, listOf(player.username))
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
            lobbyUseCase.observeLobbyMessages().collect { event ->
                when (event) {
                    is LobbyEvent.SelfJoined -> handlePlayerUpdates(event.player.toAppPlayer(), isMe = true)
                    is LobbyEvent.PlayerJoined -> handlePlayerUpdates(event.player.toAppPlayer())
                }
            }

        }

        launchInScope {
            delay(1000)
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
        val PLAYERS = emptyList<LobbyContract.LobbyPlayer>()
    }
}