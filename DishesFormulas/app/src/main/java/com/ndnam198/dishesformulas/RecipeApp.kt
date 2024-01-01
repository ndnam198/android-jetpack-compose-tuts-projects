package com.ndnam198.dishesformulas

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ndnam198.dishesformulas.core.domain.models.Category
import com.ndnam198.dishesformulas.features.recipe.presentation.pages.CategoryDetailScreen
import com.ndnam198.dishesformulas.features.recipe.presentation.pages.RecipeScreen
import com.ndnam198.dishesformulas.navigation.ScreenRoutes

@Composable
fun RecipeApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenRoutes.recipeScreen.route) {
        composable(ScreenRoutes.recipeScreen.route) {
            RecipeScreen(navigateToDetailScreen = {
                navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                navController.navigate(ScreenRoutes.categoryDetailScreen.route)
            })
        }
        composable(ScreenRoutes.categoryDetailScreen.route) {
            val category =
                navController.previousBackStackEntry?.savedStateHandle?.get<Category>("cat")
                    ?: Category.empty
            CategoryDetailScreen(category = category, navigateToRecipeScreen = {
                navController.navigate(ScreenRoutes.recipeScreen.route)
            })
        }
    }
}