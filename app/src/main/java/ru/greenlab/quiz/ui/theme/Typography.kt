package ru.greenlab.quiz.ui.theme

import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.sp
import ru.greenlab.quiz.R

private val appFontFamily = FontFamily(
    fonts = listOf(
        Font(
            resId = R.font.golos_text_vf,
            weight = FontWeight.Normal,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.golos_text_medium,
            weight = FontWeight.Medium
        ),
        Font(
            resId = R.font.golos_text_black,
            weight = FontWeight.Black
        ),
        Font(
            resId = R.font.golos_text_bold,
            weight = FontWeight.Bold
        )
    )
)

val typography = Typography(
    defaultFontFamily = appFontFamily
)
