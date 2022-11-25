package ru.greenlab.quiz.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import ru.greenlab.quiz.R

val green = Color(R.color.primaryGreen)
val greenDark = Color(R.color.primaryDark)
val greenVariant = Color(R.color.primaryVariant)

val white = Color(R.color.white)
val black = Color(R.color.black)
val grey = Color(R.color.grey)

val blue = Color(R.color.secondaryBlue)
val blueLight = Color(R.color.secondaryVariant)

val LightColors = lightColors(
    primary = green,
    primaryVariant = greenDark,
    onPrimary = white,
    surface = white,
    onSurface = black
)

val DarkColors = darkColors(
    primary = greenDark,
    surface = blue,
    onSurface = white,
    onPrimary = white,
)
