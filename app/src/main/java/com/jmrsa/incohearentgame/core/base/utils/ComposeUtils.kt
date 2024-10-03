package com.jmrsa.incohearentgame.core.base.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Suppress("ComposableNaming")
@Composable
fun <T> Flow<T>.collectInLaunchedEffect(function: suspend CoroutineScope.(value : T) -> Unit) {
    val flow = this
    LaunchedEffect(flow) {
        flow.collectLatest { function(it) }
    }
}

