package com.jmrsa.incohearentgame.presentation.utils.formatters

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.jmrsa.incohearentgame.presentation.models.AppNotificationMessage

object NotificationFormatter {
    @Composable
    fun toLobbyNotification(notification: AppNotificationMessage) =
        stringResource(notification.resource, *notification.args.toTypedArray())
}
