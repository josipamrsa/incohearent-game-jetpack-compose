package com.jmrsa.incohearentgame.presentation.screens.login

import androidx.lifecycle.viewModelScope
import com.jmrsa.data.networking.api.realtime_client.RealtimeClient
import com.jmrsa.domain.repositories.PlayerRepository
import com.jmrsa.incohearentgame.core.base.BaseViewModel
import com.jmrsa.incohearentgame.presentation.models.AppPlayer
import com.jmrsa.incohearentgame.presentation.models.toPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val playerRepository: PlayerRepository
) : BaseViewModel(), LoginContract {
    private val mutableState = MutableStateFlow(LoginContract.State("Josipa"))
    override val state = mutableState.asStateFlow()

    private val mutableEffect = MutableSharedFlow<LoginContract.Effect>()
    override val effect = mutableEffect.asSharedFlow()

    override fun onEvent(event: LoginContract.Event) {
        when (event) {
            is LoginContract.Event.OnUsernameChanged -> handleUsernameChanged(event.name)
            is LoginContract.Event.OnContinueLogin -> {
                launchInScope {
                    mutableEffect.emitInScope(LoginContract.Effect.ContinueLogin)
                }
            }
        }
    }

    private fun handleUsernameChanged(name: String) {
        mutableState.update { it.copy(username = name) }
    }
}