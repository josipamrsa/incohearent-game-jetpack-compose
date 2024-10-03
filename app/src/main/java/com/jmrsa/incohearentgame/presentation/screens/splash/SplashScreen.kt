package com.jmrsa.incohearentgame.presentation.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import com.jmrsa.incohearentgame.core.navigation.Destination
import kotlinx.serialization.Serializable

@Serializable
object SplashDestination: Destination

fun NavGraphBuilder.splashScreen() {

}

@Composable
fun SplashScreen() {

}

@Preview
@Composable
fun PreviewSplashScreen() {
    SplashScreen()
}