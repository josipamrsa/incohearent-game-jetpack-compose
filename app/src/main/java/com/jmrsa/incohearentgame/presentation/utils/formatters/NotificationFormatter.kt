package com.jmrsa.incohearentgame.presentation.utils.formatters

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.jmrsa.incohearentgame.R

object NotificationFormatter {
    @Composable
    fun toLobbyNotification(args: List<String>) =
        stringResource(R.string.inc_notif_new_player_joined, *args.toTypedArray())
}