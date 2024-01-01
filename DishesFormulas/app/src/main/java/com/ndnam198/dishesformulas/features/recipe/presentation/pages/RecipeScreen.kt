package com.ndnam198.dishesformulas.features.recipe.presentation.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ndnam198.dishesformulas.core.domain.models.Category
import com.ndnam198.dishesformulas.features.recipe.presentation.widgets.CategoryView


@Composable
fun RecipeScreen(navigateToDetailScreen: (Category) -> Unit, modifier: Modifier = Modifier) {
    val categoryViewModel: RecipeViewModel = viewModel()
    val state: RecipeViewModel.RecipeState by categoryViewModel.state

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.loading -> {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }

            state.error != null -> {
                Text("Error occurred")
            }

            else -> {
                CategoryView(navigateToDetailScreen, categories = state.categories)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryScreenPreview() {
//    RecipeScreen()
}