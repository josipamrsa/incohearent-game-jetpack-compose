package com.jmrsa.incohearentgame.navigation.utils

import androidx.navigation.NavController
import com.jmrsa.incohearentgame.navigation.Destination

fun <T: Destination> NavController.navigateWithoutBackstack(route: T) {
    navigate(route) {
        popUpTo(graph.id) { inclusive = true }
    }
}