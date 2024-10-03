package com.jmrsa.incohearentgame.core.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
inline fun <reified T : BaseViewModel> composeViewModel(
    viewModelProvider: @Composable () -> T = { hiltViewModel<T>() }
): T {
    val viewModel = viewModelProvider()
    val isLoading by viewModel.loadingFlow.collectAsStateWithLifecycle()
    val error by viewModel.errorFlow.collectAsStateWithLifecycle()

    // TODO loading and error composables

    return viewModel
}