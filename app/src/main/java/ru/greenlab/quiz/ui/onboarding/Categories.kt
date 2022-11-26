package ru.greenlab.quiz.ui.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.appcompattheme.AppCompatTheme
import ru.greenlab.quiz.R
import ru.greenlab.quiz.dto.Category
import ru.greenlab.quiz.ui.theme.grey

@Composable
fun Categories(
    categories: List<Category>,
    onClick: (Category) -> Unit
) {
    LazyColumn {
        items(categories) { category ->
            CategoryItem(category) {
                onClick(category)
            }
        }
    }
}

@Composable
private fun CategoryItem(
    category: Category,
    onClick: (Category) -> Unit
) {
    Card(
        border = BorderStroke(width = 4.dp, color = Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp)
            .clickable { onClick(category) }
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)) {
                Text(
                    text = category.name,
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

            category.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body1,
                    color = grey
                )
            }
        }
    }
}

@Preview
@Composable
private fun ItemPreview() {
    AppCompatTheme {
        CategoryItem(category = Category(
            "123",
            "AAAAAAAAAAAAAAA",
            "This is cool"
        )) {}
    }
}
