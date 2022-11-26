package ru.greenlab.quiz.ui.onboarding

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.greenlab.quiz.R
import com.google.accompanist.appcompattheme.AppCompatTheme
import ru.greenlab.quiz.dto.Category
import ru.greenlab.quiz.ui.theme.grey

enum class OnboardState {
    First, Second, Third
}

@Composable
fun OnBoardScreen(
    categories: List<Category>,
    onCategoryClick: (Category) -> Unit,
    onUnknownClick: () -> Unit
) {
    var currentScreen by remember { mutableStateOf(OnboardState.First) }

    when (currentScreen) {
        OnboardState.First -> {
            FirstScreen {
                currentScreen = OnboardState.Second
            }
        }
        OnboardState.Second -> {
            SecondScreen {
                currentScreen = OnboardState.Third
            }
        }

        OnboardState.Third -> {
            ThirdScreen(
                categories =  categories,
                onCategoryClick = onCategoryClick,
                onUnknownClick = onUnknownClick
            )
        }
    }
}

@Composable
private fun FirstScreen(
    onClick: () -> Unit
) {
    Box(modifier = Modifier.clickable { onClick() }) {
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

@Composable
private fun SecondScreen(
    onClick: () -> Unit
)  {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp, vertical = 40.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Карьерный путь",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = stringResource(id = R.string.onboard_career),
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp
        )
    }

    Box {
        Image(
            painter = painterResource(id = R.drawable.long_logo),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            contentDescription = null
        )
        Image(
            painter = painterResource(id = R.drawable.chel2),
            modifier = Modifier
                .scale(1.5f)
                .rotate(45f)
                .padding(100.dp)
                .align(Alignment.BottomCenter),
            contentDescription = null
        )
    }
}

@Composable
private fun ThirdScreen(
    categories: List<Category>,
    onCategoryClick: (Category) -> Unit,
    onUnknownClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = R.string.onboard_directions),
            style = MaterialTheme.typography.h4,
            textDecoration = TextDecoration.Underline
        )

        Categories(categories = categories, onClick = onCategoryClick)

        Text(
            text = stringResource(id = R.string.onboard_dont_know),
            fontWeight = FontWeight.Bold,
            color = grey,
            modifier = Modifier.padding(top = 25.dp, bottom = 15.dp)
        )

        Card(
            border = BorderStroke(width = 4.dp, color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
                .clickable { onUnknownClick() }
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start)
                ) {
                    Text(
                        text = stringResource(id = R.string.onboard_help),
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Icon(
                        modifier = Modifier
                            .scale(2.5f)
                            .padding(end = 3.dp, top = 3.dp),
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = null
                    )
                }
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun OnBoardPreview_Light() {
    AppCompatTheme {
        OnBoardScreen(emptyList(), {}, {})
    }
}

@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun OnBoardPreview_Dark() {
    AppCompatTheme {
        OnBoardScreen(emptyList(), {}, {})
    }
}
