package ru.greenlab.quiz.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import ru.greenlab.quiz.R

private val regular = Font(R.font.golos_text_vf, FontWeight.Normal)
private val medium = Font(R.font.golos_text_medium, FontWeight.Medium)
private val bold = Font(R.font.golos_text_bold, FontWeight.Bold)

private val appFontFamily = FontFamily(
    fonts = listOf(regular, medium, bold)
)

val typography = Typography(
    defaultFontFamily = appFontFamily,
    button = TextStyle(
        color = Color.Unspecified
    )
)
