package com.motion.hydropome.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.motion.hydropome.R

val plusJakartaSansFontFamily = FontFamily(
    Font(R.font.plus_jakarta_sans_w400, FontWeight.W400),
    Font(R.font.plus_jakarta_sans_w700, FontWeight.W600),
    Font(R.font.plus_jakarta_sans_w700, FontWeight.W700)
)

val Typography = Typography(
    displayLarge = TextStyle(fontFamily = plusJakartaSansFontFamily),
    displayMedium = TextStyle(fontFamily = plusJakartaSansFontFamily),
    displaySmall = TextStyle(fontFamily = plusJakartaSansFontFamily),
    headlineLarge = TextStyle(fontFamily = plusJakartaSansFontFamily),
    headlineMedium = TextStyle(fontFamily = plusJakartaSansFontFamily),
    headlineSmall = TextStyle(fontFamily = plusJakartaSansFontFamily),
    titleLarge = TextStyle(fontFamily = plusJakartaSansFontFamily),
    titleMedium = TextStyle(fontFamily = plusJakartaSansFontFamily),
    titleSmall = TextStyle(fontFamily = plusJakartaSansFontFamily),
    bodyLarge = TextStyle(fontFamily = plusJakartaSansFontFamily),
    bodyMedium = TextStyle(fontFamily = plusJakartaSansFontFamily),
    bodySmall = TextStyle(fontFamily = plusJakartaSansFontFamily),
    labelLarge = TextStyle(fontFamily = plusJakartaSansFontFamily),
    labelMedium = TextStyle(fontFamily = plusJakartaSansFontFamily),
    labelSmall = TextStyle(fontFamily = plusJakartaSansFontFamily)
)