package ru.greenlab.quiz.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
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

val greenLabLight = lightColors(
    primary = green,
    onPrimary = white,
    secondary = blueLight,
    onSecondary = black,
    surface = grey
)

val greenLabDark = darkColors(
    primary = greenDark,
    surface = grey,
    onSurface = white,
    onPrimary = white
)

@Composable
fun GreenLabTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val appColors = when (darkTheme) {
        true -> greenLabDark
        false -> greenLabLight
    }

    MaterialTheme(
        colors = appColors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
