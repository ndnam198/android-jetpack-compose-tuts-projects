package com.ndnam198.dishesformulas.features.recipe.presentation.pages

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ndnam198.dishesformulas.core.domain.models.Categories
import com.ndnam198.dishesformulas.core.network.recipeService
import kotlinx.coroutines.launch
import java.lang.Exception

class RecipeViewModel : ViewModel() {
    private val _state = mutableStateOf(RecipeState())
    val state: State<RecipeState> = _state

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        _state.value = _state.value.copy(loading = true)

        viewModelScope.launch {
            try {
                val result = recipeService.getCategories()
                _state.value = _state.value.copy(
                    loading = false,
                    categories = result,
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    loading = false, error = "Error fetching Categories: ${e.message}"
                )
            }
        }
    }

    data class RecipeState(
        val loading: Boolean = true,
        val categories: Categories = Categories(emptyList()),
        val error: String? = null
    )
}