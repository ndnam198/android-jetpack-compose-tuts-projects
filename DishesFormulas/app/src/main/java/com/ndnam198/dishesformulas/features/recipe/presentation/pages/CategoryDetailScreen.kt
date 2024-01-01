package com.ndnam198.dishesformulas.features.recipe.presentation.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ndnam198.dishesformulas.core.domain.models.Category

@Composable
fun CategoryDetailScreen(navigateToRecipeScreen: () -> Unit, category: Category) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {


        Text(
            modifier = Modifier.fillMaxWidth(),
            text = category.strCategory,
            color = Color.White,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium
        )

        Image(
            painter = rememberAsyncImagePainter(
                model = category.strCategoryThumb
            ),
            contentDescription = category.strCategory,
            modifier = Modifier
                .aspectRatio(1f)
        )

        Text(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            text = category.strCategoryDescription,
            color = Color.White,
            textAlign = TextAlign.Justify,
            style = TextStyle(
                lineHeight = 22.sp,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp
            )
        )
    }
}

