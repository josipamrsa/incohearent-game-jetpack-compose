package com.jmrsa.incohearentgame.presentation.shared.base

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ScaffoldBaseScreen(
    modifier: Modifier = Modifier,
    scaffoldModifier: Modifier = Modifier,
    scaffoldContainerColor: Color = Color.Transparent,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    background: @Composable () -> Unit = {},
    isScrollEnabled: Boolean = true,
    scrollState: ScrollState = rememberScrollState(),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    content: @Composable ColumnScope.() -> Unit
) {
    val scrollModifier = remember {
        if (isScrollEnabled)
            Modifier.verticalScroll(state = scrollState)
        else Modifier
    }

    Scaffold(
        topBar = topBar,
        bottomBar = bottomBar,
        containerColor = scaffoldContainerColor,
        modifier = scaffoldModifier.imePadding()
    ) { contentPadding ->
        background()

        BaseScreenContent(
            modifier = modifier
                .fillMaxSize()
                .then(scrollModifier)
                .padding(contentPadding),
            horizontalAlignment = horizontalAlignment,
            verticalArrangement = verticalArrangement,
            content = content
        )
    }
}

@Composable
fun BaseScreenContent(
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal,
    verticalArrangement: Arrangement.Vertical,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = verticalArrangement,
        content = content
    )
}