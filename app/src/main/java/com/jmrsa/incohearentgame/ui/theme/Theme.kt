package com.jmrsa.incohearentgame.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val lightColorScheme = lightColorScheme(
    primary = IncohearentBasePrimary,
    onPrimary = IncohearentBaseOnPrimary,
    primaryContainer = IncohearentBasePrimaryContainer,
    onPrimaryContainer = IncohearentBaseOnPrimaryContainer,
    secondary = IncohearentBaseSecondary,
    onSecondary = IncohearentBaseOnSecondary,
    secondaryContainer = IncohearentBaseSecondaryContainer,
    onSecondaryContainer = IncohearentBaseOnSecondaryContainer,
    tertiary = IncohearentBaseTertiary,
    onTertiary = IncohearentBaseOnTertiary,
    tertiaryContainer = IncohearentBaseTertiaryContainer,
    onTertiaryContainer = IncohearentBaseOnTertiaryContainer,
    error = IncohearentBaseError,
    onError = IncohearentBaseOnError,
    errorContainer = IncohearentBaseErrorContainer,
    onErrorContainer = IncohearentBaseOnErrorContainer,
    background = IncohearentBaseBackground,
    onBackground = IncohearentBaseOnBackground,
    surface = IncohearentBaseSurface,
    onSurface = IncohearentBaseOnSurface,
    surfaceVariant = IncohearentBaseSurfaceVariant,
    onSurfaceVariant = IncohearentBaseOnSurfaceVariant,
    outline = IncohearentBaseOutline,
    outlineVariant = IncohearentBaseOutlineVariant,
    scrim = IncohearentBaseScrim,
    inverseSurface = IncohearentBaseInverseSurface,
    inverseOnSurface = IncohearentBaseInverseOnSurface,
    inversePrimary = IncohearentBaseInversePrimary,
    surfaceDim = IncohearentBaseSurfaceDim,
    surfaceBright = IncohearentBaseSurfaceBright,
    surfaceContainerLowest = IncohearentBaseSurfaceContainerLowest,
    surfaceContainerLow = IncohearentBaseSurfaceContainerLow,
    surfaceContainer = IncohearentBaseSurfaceContainer,
    surfaceContainerHigh = IncohearentBaseSurfaceContainerHigh,
    surfaceContainerHighest = IncohearentBaseSurfaceContainerHighest,
)

@Composable
fun IncohearentGameTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }

    MaterialTheme(
        colorScheme = lightColorScheme,
        typography = Typography,
        content = content
    )
}