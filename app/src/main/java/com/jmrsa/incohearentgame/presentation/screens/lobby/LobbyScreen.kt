package com.jmrsa.incohearentgame.presentation.screens.lobby

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LobbyScreen() {
    val players = listOf(
        "Luke",
        "Christopher",
        "Samuel",
        "Peter",
        "Johnathan",
        "James",
        "Aydan",
        "Matthew",
        "Andrew"
    ).chunked(4)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp)
    ) {
        items(players) { row ->
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                row.forEach { player ->
                    AssistChip(onClick = { /*TODO*/ }, label = {
                        Text(text = player, color = Color.White)
                    })
                }
            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillParentMaxHeight(0.8f)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .background(Color.DarkGray)
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Text(text = "Info zone", color = Color.White)
                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    Text(text = "Begin game")
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewLobbyScreen() {
    LobbyScreen()
}