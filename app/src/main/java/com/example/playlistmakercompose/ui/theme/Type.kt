package com.example.playlistmakercompose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.playlistmakercompose.R


val YSDisplayFamily = FontFamily(
    Font(R.font.ys_display_regular, FontWeight.Normal),
    Font(R.font.ys_display_medium, FontWeight.W500)
)
val YSDisplayMediumW400 = FontFamily(
    Font(R.font.ys_display_medium, FontWeight.Normal)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = YSDisplayFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ),
    bodySmall = TextStyle(
        fontFamily = YSDisplayFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        lineHeight = 11.sp,
        letterSpacing = 0.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = YSDisplayMediumW400,
        fontWeight = FontWeight.Normal,
        fontSize = 19.sp,
        lineHeight = 19.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = YSDisplayFamily,
        fontWeight = FontWeight.W500,
        fontSize = 22.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.sp
    ),
    titleSmall = TextStyle(
        fontFamily = YSDisplayFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.sp
    ),
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