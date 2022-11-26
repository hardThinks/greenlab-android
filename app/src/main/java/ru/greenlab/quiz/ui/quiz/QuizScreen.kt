package ru.greenlab.quiz.ui.quiz

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.appcompattheme.AppCompatTheme
import ru.greenlab.quiz.R
import ru.greenlab.quiz.dto.Question
import ru.greenlab.quiz.ui.theme.grey

@Composable
fun QuizScreen(
    questions: List<Question>,
    onSubmitClick: (String) -> Unit,
    onFinalSubmit: () -> Unit
) {
    val currentPosition = remember { mutableStateOf(0) }

    val radioOptions = listOf(
        Answers.yes[1],
        Answers.mostlyYes[1],
        Answers.dontKnow[1],
        Answers.mostlyNo[1],
        Answers.no[1]
    )

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[2]) }

    Column(
        modifier = Modifier.padding(horizontal =  20.dp, vertical = 40.dp)
    ) {

        TestHeader()
        Spacer(modifier = Modifier.padding(40.dp))

        QuizItem(
            question = questions[currentPosition.value],
            radioOptions = radioOptions,
            onSubmitClick = {
                onSubmitClick(selectedOption)
                if (currentPosition.value < questions.lastIndex)
                    currentPosition.value++
                else {
                    onSubmitClick(selectedOption)
                    onFinalSubmit()
                }
            },
            selectedOption = selectedOption,
            onOptionSelected = onOptionSelected
        )
    }
}

@Composable
private fun QuizItem(
    question: Question,
    radioOptions: List<String>,
    onSubmitClick: () -> Unit,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Column {
        Text(
            text = question.value,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = stringResource(id = R.string.test_choose),
            modifier = Modifier.padding(top = 15.dp),
            color = grey,
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.padding(top = 10.dp))

        Column {
            radioOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) }
                        )
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) }
                    )
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = text,
                        fontSize = 20.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onSubmitClick
        ) {
            Text(text = "Продолжить", fontSize = 20.sp)
        }

    }
}

@Composable
private fun TestHeader() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.test_name),
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

@Preview(showSystemUi = true)
@Composable
private fun QuizScreenPreview_Light() {
    AppCompatTheme {
        QuizScreen(
            questions = listOf(
                Question("1", "Whaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaat?"),
                Question("2", "What?"),
                Question("3", "Whaaaaaat?")
            ),
            onSubmitClick = {},
            onFinalSubmit = {}
        )
    }
}

@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun QuizScreenPreview_Dark() {
    AppCompatTheme {
        QuizScreen(
            questions = listOf(
                Question("1", "Whaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaat?"),
                Question("2", "What?"),
                Question("3", "What?")
            ),
            onSubmitClick = {},
            onFinalSubmit = {}
        )
    }
}
