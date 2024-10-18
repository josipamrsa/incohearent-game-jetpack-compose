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
import com.jmrsa.incohearentgame.presentation.utils.DUMMY_PLAYERS
import com.jmrsa.incohearentgame.presentation.utils.DUMMY_PLAYER_COLORS
import com.jmrsa.incohearentgame.ui.theme.IncohearentGameTheme

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
    playerColors: List<Color>,
    sections: Int = 4
) {

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
                        color = playerColors.random()
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
    IncohearentGameTheme {
        val players = DUMMY_PLAYERS
        val colors = DUMMY_PLAYER_COLORS

        PlayerGridView(
            playerList = players,
            playerColors = colors
        )
    }
}