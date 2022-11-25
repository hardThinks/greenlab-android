package ru.greenlab.quiz.ui.register

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.greenlab.quiz.R
import ru.greenlab.quiz.ui.theme.GreenLabTheme
import ru.greenlab.quiz.ui.theme.typography

@Composable
fun RegisterScreen(
    onSubmit: () -> Unit
) {
    var inputName by remember { mutableStateOf("") }
    var inputNumber by remember { mutableStateOf("") }
    var inputCity by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Logo()

        Text(
            modifier = Modifier.padding(top = 70.dp, bottom = 20.dp),
            text = "Регистрация",
            style = typography.h4,
            textAlign = TextAlign.Center
        )

        InputField(
            label = "ФИО",
            text = inputName
        ) {
            inputName = it
        }

        InputField(
            label = "Номер телефона",
            text = inputNumber
        ) {
            inputNumber = it
        }

        InputField(
            label = "Город проведения мероприятия",
            text = inputCity
        ) {
            inputCity = it
        }

        RegisterButton(
            modifier = Modifier.padding(top = 100.dp),
            onClick = onSubmit
        )
    }
}

@Composable
fun RegisterButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
    ) {
        Text(
            text = "Зарегистрировать",
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun Logo(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(end = 100.dp)
        ,
        painter = painterResource(
            id = if (isSystemInDarkTheme()) R.drawable.logo
                 else R.drawable.logo
        ),
        contentDescription = null,
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun InputField(
    label: String,
    text: String,
    onChange: (String) -> Unit
) {
    OutlinedTextField(
        value = text,
        onValueChange = onChange,
        label = { Text(label) }
    )
}


@Composable
@Preview(showSystemUi = true)
private fun RegisterScreenPreview_Light() {
    GreenLabTheme {
        RegisterScreen() {}
    }
}

@Composable
@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
private fun RegisterScreenPreview_Dark() {
    GreenLabTheme {
        RegisterScreen() {}
    }
}
