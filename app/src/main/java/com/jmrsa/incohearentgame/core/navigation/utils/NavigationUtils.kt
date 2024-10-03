package com.jmrsa.incohearentgame.core.navigation.utils

import androidx.navigation.NavController
import com.jmrsa.incohearentgame.core.navigation.Destination

fun <T: Destination> NavController.navigateWithoutBackstack(route: T) {
    navigate(route) {
        popUpTo(graph.id) { inclusive = true }
    }
}