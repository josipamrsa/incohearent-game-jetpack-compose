package com.jmrsa.incohearentgame.presentation.screens.login

import com.jmrsa.incohearentgame.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel(), LoginContract {
    private val mutableState = MutableStateFlow(LoginContract.State())
    override val state = mutableState.asStateFlow()

    private val mutableEffect = MutableSharedFlow<LoginContract.Effect>()
    override val effect = mutableEffect.asSharedFlow()

    override fun onEvent(event: LoginContract.Event) {
        when (event) {
            is LoginContract.Event.OnUsernameChanged -> handleUsernameChanged(event.name)
            LoginContract.Event.OnContinueLogin -> {
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