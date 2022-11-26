package ru.greenlab.quiz.ui.onboarding

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.greenlab.quiz.ui.theme.GreenLabTheme

@Composable
fun OnBoardScreen() {
    Row {

    }
}

@Preview(showSystemUi = true)
@Composable
fun OnBoardPreview_Light() {
    GreenLabTheme {
        OnBoardScreen()
    }
}

@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun OnBoardPreview_Dark() {
    GreenLabTheme {
        OnBoardScreen()
    }
}
