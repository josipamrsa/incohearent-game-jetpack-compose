package com.jmrsa.incohearentgame.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.jmrsa.incohearentgame.navigation.utils.navigateWithoutBackstack
import com.jmrsa.incohearentgame.presentation.screens.lobby.LobbyDestination
import com.jmrsa.incohearentgame.presentation.screens.lobby.lobbyScreen
import com.jmrsa.incohearentgame.presentation.screens.login.LoginDestination
import com.jmrsa.incohearentgame.presentation.screens.login.loginScreen

@Composable
fun MainNavComponent(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = LoginDestination,
        modifier = modifier
    ) {
        loginScreen(
            onNavigateToLobby = { navController.navigateWithoutBackstack(LobbyDestination) }
        )
        lobbyScreen()
    }
}