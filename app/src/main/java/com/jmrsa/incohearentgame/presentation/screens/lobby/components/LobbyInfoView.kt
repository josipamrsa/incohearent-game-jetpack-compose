package com.jmrsa.incohearentgame.presentation.screens.lobby.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LobbyInfoView(notifications: List<String>? = null) {
    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .background(
                color = Color.DarkGray,
                shape = RoundedCornerShape(5.dp)
            )
    ) {
        if (notifications.isNullOrEmpty()) {
            item {
                Text(
                    text = "No new notifications",
                    color = Color.White,
                    modifier = Modifier.padding(
                        vertical = 5.dp,
                        horizontal = 15.dp
                    )
                )
            }

        } else {
            items(notifications) { notification ->
                Text(
                    text = notification,
                    color = Color.White,
                    modifier = Modifier.padding(
                        vertical = 5.dp,
                        horizontal = 15.dp
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewLobbyInfoView() {
    val notifs = listOf(
        "Player snitch has joined the lobby!",
        "Player damn has changed the lobby name!",
        "Player sonny boy has left the lobby!"
    )
    LobbyInfoView(notifs)
}

@Preview
@Composable
fun PreviewEmptyLobbyInfoView() {
    LobbyInfoView()
}