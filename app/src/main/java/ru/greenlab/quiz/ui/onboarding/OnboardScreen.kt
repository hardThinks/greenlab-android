package ru.greenlab.quiz.ui.onboarding

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.greenlab.quiz.R
import com.google.accompanist.appcompattheme.AppCompatTheme

enum class OnboardState {
    first, second, third
}

@Composable
fun OnBoardScreen() {
    var currentScreen by remember { mutableStateOf(OnboardState.first) }

    when (currentScreen) {
        OnboardState.first -> {
            firstScreen()
        }
        OnboardState.second -> {

        }

        OnboardState.third -> {

        }
    }
}

@Composable
private fun firstScreen() {
    Box {
        Image(
            painter = painterResource(id = R.drawable.rectangle),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomEnd),
            contentScale = ContentScale.FillBounds
        )

        Image(
            modifier = Modifier
                .scale(1.8f)
                .align(Alignment.CenterEnd)
                .padding(top = 140.dp),
            painter = painterResource(id = R.drawable.chel1),
            contentDescription = null
        )

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                modifier = Modifier
                    .padding(top = 60.dp),
                text = stringResource(id = R.string.onboard_hello),
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = Modifier
                    .padding(top = 50.dp),
                text = stringResource(id = R.string.onboard_stud_info),
                style = MaterialTheme.typography.body1,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = Modifier
                    .padding(top = 90.dp)
                    .width(190.dp),
                text = stringResource(id = R.string.onboard_offer),
                style = MaterialTheme.typography.body2,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun OnBoardPreview_Light() {
    AppCompatTheme {
        OnBoardScreen()
    }
}

@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun OnBoardPreview_Dark() {
    AppCompatTheme {
        OnBoardScreen()
    }
}
