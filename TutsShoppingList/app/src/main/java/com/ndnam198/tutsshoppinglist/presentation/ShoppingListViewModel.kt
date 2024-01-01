package com.ndnam198.tutsshoppinglist.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ndnam198.tutsshoppinglist.domain.entities.ShoppingItem
import kotlin.random.Random


class ShoppingListViewModel : ViewModel() {
    var showDialog by mutableStateOf(false)

    var sItems by mutableStateOf(listOf<ShoppingItem>())

    var editingId by mutableIntStateOf(-1)

    fun confirmDialog(name: String, quantity: String) {
        showDialog = false
        if (name.isNotEmpty() && quantity.isNotEmpty()) {
            if (editingId > 0) {
                // edit existing item
                sItems = sItems.map {
                    if (it.id == editingId) it.copy(
                        name = name, quantity = quantity.toInt()
                    ) else it
                }
            } else {
                // add new item
                sItems += ShoppingItem(
                    id = Random.nextInt(1, Int.MAX_VALUE), name = name, quantity = quantity.toInt()
                )
            }
        }
        editingId = -1
    }

    fun deleteItem(item: ShoppingItem) {
        sItems -= item
    }

    fun editItem(id: Int) {
        editingId = id
        showDialog = true
    }

}