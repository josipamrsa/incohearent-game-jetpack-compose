package com.jmrsa.incohearentgame.presentation.screens.splash

import com.jmrsa.incohearentgame.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class SplashViewModel @Inject constructor() : BaseViewModel(), SplashContract {
    private val mutableState = MutableStateFlow(SplashContract.State)
    override val state = mutableState.asStateFlow()

    private val mutableEffect = MutableSharedFlow<SplashContract.Effect>()
    override val effect = mutableEffect.asSharedFlow()

    override fun onEvent(event: SplashContract.Event) {

    }
}