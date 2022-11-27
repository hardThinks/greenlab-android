package ru.greenlab.quiz.ui.result

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.appcompattheme.AppCompatTheme
import ru.greenlab.quiz.R
import ru.greenlab.quiz.dto.Category
import ru.greenlab.quiz.dto.SingleUserResult

const val offer = "https://edu.greenatom.ru/caselab/"

@Composable
fun ResultScreen(
    results: List<SingleUserResult>
) {
    Column {
        Header()
        LazyColumn(
            modifier = Modifier
                .padding(vertical =  20.dp, horizontal = 30.dp)
        ) {
            items(results) { item ->
                ResultItem(result = item)
            }
        }
    }
}

@Composable
private fun ResultItem(result: SingleUserResult) {
    Column(modifier = Modifier.padding(bottom = 25.dp)) {
        val uriHandler = LocalUriHandler.current

        Text(
            text = result.category.name,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        )
        CustomProgressBar(progress = result.percentage)
        OutlinedButton(
            modifier = Modifier.fillMaxWidth().padding(top = 3.dp),
            onClick = { uriHandler.openUri(offer) },
            border = BorderStroke(width = 3.dp, color = MaterialTheme.colors.primary)
        ) {
            Text(
                text = "Узнать про компетенцию",
                fontSize = 18.sp,
                color = Color.Black
            )
        }
    }
}

@Composable
private fun Header() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(30.dp)) {
        Text(
            text = stringResource(id = R.string.result_header),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )

        Image(
            painter = painterResource(
                id = if (isSystemInDarkTheme()) R.drawable.logo
                else R.drawable.logo
            ),
            contentDescription = null,
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
}

private val samples = listOf(
    SingleUserResult(Category("1", "Первая категория"), 42),
    SingleUserResult(Category("2", "Вторая категория"), 100),
    SingleUserResult(Category("3", "Вторая категория"), 30),
    SingleUserResult(Category("4", "Первая категория"), 12),
    SingleUserResult(Category("5", "Вторая категория"), 0),
    SingleUserResult(Category("6", "Вторая категория"), 36)
)

@Composable
@Preview(showSystemUi = true)
fun ResultScreenPreview_Light() {
    AppCompatTheme {
        ResultScreen(samples)
    }
}

@Composable
@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
fun ResultScreenPreview_Dark() {
    AppCompatTheme {
        ResultScreen(samples)
    }
}
