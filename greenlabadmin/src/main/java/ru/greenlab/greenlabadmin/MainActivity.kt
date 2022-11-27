package ru.greenlab.greenlabadmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
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
    LazyColumn(modifier = Modifier.padding(20.dp)) {
        items(users) {
            UserItem(it)
        }
    }
}

@Composable
fun UserItem(user: User) {
    Card(elevation = 10.dp, modifier = Modifier.padding(vertical = 20.dp)) {
        Column {
            Text(
                text = user.name + ", " + user.city,
                style = MaterialTheme.typography.h5
            )
            Text(text = user.phone_number, fontSize = 20.sp)
            Divider()

            for (item in user.quiz_results.last().quiz_result_items) {
                Row {
                    Text(item.category.name + ": ")
                    Text(item.percentage.toString() + '%')
                }
            }
        }
    }
}

//@Composable
//fun Categories(categories: SingleResult) {
//    LazyColumn {
//        items(categories.quiz_result_items) {
//            CategoryItem(it)
//        }
//    }
//}

@Composable
fun CategoryItem(categoryItem: CategoryAndPercent) {
    Row {
        Text(text = categoryItem.category.name)
        Text(text = categoryItem.percentage.toString() + '%')
    }
}

@Preview(showBackground = true)
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
