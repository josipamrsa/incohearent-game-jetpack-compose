package com.jmrsa.incohearentgame.base.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext

@Suppress("ComposableNaming")
@Composable
fun <T> Flow<T>.collectInLaunchedEffect(function: suspend CoroutineScope.(value : T) -> Unit) {
    val flow = this
    LaunchedEffect(flow) {
        flow.collectLatest { function(it) }
    }
}

