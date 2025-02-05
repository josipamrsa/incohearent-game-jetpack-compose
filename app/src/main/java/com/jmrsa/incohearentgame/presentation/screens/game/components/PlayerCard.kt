package com.jmrsa.incohearentgame.presentation.screens.game.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jmrsa.incohearentgame.ui.theme.IncohearentGameTheme
import com.jmrsa.incohearentgame.R

@Composable
fun PlayerPoints(
    points: Int
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(45.dp)
            .height(45.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            )
    ) {
        Text(
            text = "${points}pts",
            color = MaterialTheme.colorScheme.onTertiary,
            fontSize = 12.sp
        )
    }
}

@Composable
fun JudgeLabel() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(45.dp)
            .height(45.dp)
            .background(
                color = MaterialTheme.colorScheme.error,
                shape = CircleShape
            )
    ) {
        Text(
            text = stringResource(R.string.inc_game_round_judge_label)
                .uppercase(),
            color = MaterialTheme.colorScheme.onTertiary,
            fontSize = 10.sp
        )
    }

}

@Composable
fun PlayerCard(
    initials: String,
    points: Int,
    isRoundJudge: Boolean = false
) {
    Box(
        contentAlignment = Alignment.BottomEnd,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(120.dp)
                .height(120.dp)
                .background(
                    color = MaterialTheme.colorScheme.tertiary,
                    shape = CircleShape
                )
        ) {
            Text(
                text = initials.uppercase(),
                color = MaterialTheme.colorScheme.onTertiary,
                fontSize = 36.sp
            )
        }

        if (isRoundJudge)
            JudgeLabel()
        else
            PlayerPoints(points)
    }

}

@Preview
@Composable
fun PreviewPlayerCard() {
    IncohearentGameTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            PlayerCard(
                initials = "JM",
                points = 16,
            )

            PlayerCard(
                initials = "BP",
                points = 16,
                isRoundJudge = true
            )
        }
    }
}