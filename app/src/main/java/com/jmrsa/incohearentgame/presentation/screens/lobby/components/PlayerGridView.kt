package com.jmrsa.incohearentgame.presentation.screens.lobby.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PlayerChip(
    label: String,
    color: Color? = null
) {
    val chipColor = color ?: Color.DarkGray

    AssistChip(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(15.dp),
        label = { Text(text = label, color = Color.White) },
        colors = AssistChipDefaults.assistChipColors().copy(
            containerColor = chipColor
        ),
        border = BorderStroke(0.dp, chipColor)
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PlayerGridView(
    playerList: List<String>,
    sections: Int = 4
) {
    val randomColors = listOf(
        Color(0xff61a0af),
        Color(0xff96c9dc),
        Color(0xfff06c9b),
        Color(0xfff9b9b7),
        Color(0xfff5d491),
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 15.dp,
                vertical = 10.dp
            )
    ) {
        items(playerList.chunked(sections)) { row ->
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                row.forEach { player ->
                    PlayerChip(
                        label = player,
                        color = randomColors.random()
                    )
                }
            }
        }}
}

@Preview
@Composable
fun PreviewPlayerChip() {
    PlayerChip("Kristofor")
}

@Preview
@Composable
fun PreviewPlayerGridView() {
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
    )

    PlayerGridView(players)
}