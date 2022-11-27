package ru.greenlab.quiz.ui.result

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.google.accompanist.appcompattheme.AppCompatTheme
import ru.greenlab.quiz.ui.theme.green
import ru.greenlab.quiz.ui.theme.greenVariant
import ru.greenlab.quiz.ui.theme.grey


@Composable
fun CustomProgressBar(
    progress: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .fillMaxHeight(),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .height(30.dp)
                .background(Color.LightGray)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .height(30.dp)
                    .background(MaterialTheme.colors.primary)
                    .fillMaxWidth(progress / 100f)
            )
            Text(
                text = "$progress %",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewIndicator() {
    AppCompatTheme {
        CustomProgressBar(progress = 10)
    }
}
