package ru.greenlab.greenlabadmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
    LazyColumn {
        
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GreenLabQuizTheme {

    }
}
