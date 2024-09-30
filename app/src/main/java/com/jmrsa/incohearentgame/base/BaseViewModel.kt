package com.jmrsa.incohearentgame.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    private val mutableErrorFlow = MutableStateFlow<AppError?>(null)
    val errorFlow = mutableErrorFlow.asStateFlow()

    private val mutableLoadingFlow = MutableStateFlow(false)
    val loadingFlow = mutableLoadingFlow.asStateFlow()

    protected fun launchInScope(
        coroutineScope: CoroutineScope = viewModelScope,
        action: suspend CoroutineScope.() -> Unit
    ): Job =
        coroutineScope.launch {
            withErrorHandling { action() }
        }

    protected fun launchInScopeWithProgress(
        coroutineScope: CoroutineScope = viewModelScope,
        onStart: () -> Unit = { mutableLoadingFlow.value = true },
        onFinish: () -> Unit = { mutableLoadingFlow.value = false },
        action: suspend CoroutineScope.() -> Unit
    ) = coroutineScope.launch {
        onStart()
        withErrorHandling { action() }
        onFinish()
    }

    protected fun <T> MutableSharedFlow<T>.emitInScope(
        value: T,
        coroutineScope: CoroutineScope = viewModelScope
    ) {
        launchInScope(coroutineScope) { emit(value) }
    }

    protected suspend fun withErrorHandling(action: suspend () -> Unit) {
        try {
            action()
        } catch (exception: Exception) {
            if (exception is CancellationException) return

            // TODO better error handling
            exception.message?.let { message ->
                mutableErrorFlow.emit(AppError(message))
            }
        }
    }

    fun clearError() {
        mutableErrorFlow.update { null }
    }

}
