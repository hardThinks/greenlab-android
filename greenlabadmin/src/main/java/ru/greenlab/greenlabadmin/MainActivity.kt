package ru.greenlab.greenlabadmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.greenlab.greenlabadmin.dto.Category
import ru.greenlab.greenlabadmin.dto.CategoryAndPercent
import ru.greenlab.greenlabadmin.dto.SingleResult
import ru.greenlab.greenlabadmin.dto.User
import ru.greenlab.greenlabadmin.ui.theme.GreenLabQuizTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreenLabQuizTheme {
                val users = viewModel.users.collectAsState()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AdminScreen(users.value)
                }
            }
        }
    }
}

@Composable
fun AdminScreen(users: List<User>) {
    Column {
        Spacer(modifier = Modifier.padding(top = 15.dp))
        Header()
        LazyColumn(modifier = Modifier.padding(horizontal = 20.dp)) {
            items(users) {
                UserItem(it)
            }
        }
    }
}

@Composable
fun UserItem(user: User) {
    Card(elevation = 10.dp, modifier = Modifier.padding(vertical = 20.dp)) {
        Column {
            Text(
                text = user.name + ", " + user.city,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
            Text(text = user.phone_number, fontSize = 20.sp)
            Divider()

            if (user.quiz_results.isNotEmpty()) {
                for (item in user.quiz_results.last().quiz_result_items) {
                    Row {
                        Text(item.category.name + ": ")
                        Text(item.percentage.toString() + '%')
                    }
                }
            }
        }
    }
}

@Composable
fun Logo(modifier: Modifier = Modifier) {
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
private fun Header() {
    Box(modifier = Modifier
        .padding(horizontal = 20.dp)
        .fillMaxWidth()) {
        Text(
            text = "Студенты",
            fontWeight = FontWeight.Bold,
            fontSize = 34.sp
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
fun DefaultPreview() {
    GreenLabQuizTheme {
        AdminScreen(
            users = listOf(
                User(
                    "Таганрог",
                    "1",
                    "Иванов Иван",
                    "79996915605",
                    listOf(
                        SingleResult(
                            listOf(
                                CategoryAndPercent(
                                    Category(
                                        "1",
                                        "Категория 1"
                                    ),
                                    percentage = 10
                                )
                            )
                        )
                    )
                )
            )
        )
    }
}
