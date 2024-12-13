package com.jmrsa.incohearentgame.core.navigation.utils

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavBackStackEntry

fun defaultEnterTransition(): (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?) =
    {
        slideInHorizontally(
            initialOffsetX = { it / 4 }
        ) + fadeIn()
    }

fun defaultExitTransition(): (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?) =
    {
        slideOutHorizontally(
            targetOffsetX = { it / 4 }
        ) + fadeOut()
    }

fun defaultPopEnterTransition(): (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?) =
    {
        slideInHorizontally(
            initialOffsetX = { it / 4 }
        ) + fadeIn()
    }

fun defaultPopExitTransition(): (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?) =
    {
        slideOutHorizontally(
            targetOffsetX = { it / 4 }
        ) + fadeOut()
    }
