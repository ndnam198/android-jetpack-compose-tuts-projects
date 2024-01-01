package com.ndnam198.tutsshoppinglist.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ndnam198.tutsshoppinglist.domain.entities.ShoppingItem
import com.ndnam198.tutsshoppinglist.presentation.widgets.ShoppingListItem
import kotlin.math.roundToInt

@Composable
fun ShoppingListView(viewModel: ShoppingListViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { viewModel.showDialog = true }) {
            Text("Add item")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(viewModel.sItems) {
                ShoppingListItem(it, { viewModel.editItem(it.id) }, { viewModel.deleteItem(it) })
            }
        }
    }

    if (viewModel.showDialog) {
        var name by remember {
            mutableStateOf("")
        }
        var quantity by remember {
            mutableStateOf("")
        }

        AlertDialog(
            onDismissRequest = {
                viewModel.showDialog = false
                viewModel.editingId = -1
            },
            text = {
                Column {
                    Text(
                        if (viewModel.editingId > 0) "Edit item" else "Add shopping item",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(Modifier.height(16.dp))
                    OutlinedTextField(value = name,
                        onValueChange = { name = it },
                        singleLine = true,
                        label = { Text("Enter name") })
                    Spacer(Modifier.height(16.dp))
                    OutlinedTextField(value = quantity.toString(),
                        onValueChange = { quantity = it },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = {
                            Text("Enter quantity")
                        })
                }
            },
            confirmButton = {
                Button(
                    onClick = { viewModel.confirmDialog(name, quantity) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text("Done")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = {
                    viewModel.showDialog = false
                    viewModel.editingId = -1
                }) {
                    Text("Cancel")
                }
            },
        )
    }
}


