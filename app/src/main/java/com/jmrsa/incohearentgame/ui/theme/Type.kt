package com.jmrsa.incohearentgame.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jmrsa.incohearentgame.R

val SpaceGroteskFontFamily = FontFamily(
    Font(resId = R.font.spacegrotesk_regular, style = FontStyle.Normal),
    Font(
        resId = R.font.spacegrotesk_semibold,
        style = FontStyle.Normal,
        weight = FontWeight.SemiBold
    ),
    Font(
        resId = R.font.spacegrotesk_bold,
        style = FontStyle.Normal,
        weight = FontWeight.Bold
    ),
    Font(
        resId = R.font.spacegrotesk_light,
        style = FontStyle.Normal,
        weight = FontWeight.Light
    ),
    Font(
        resId = R.font.spacegrotesk_regular,
        style = FontStyle.Italic,
        weight = FontWeight.Light
    ),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)