package com.ndnam198.tutsshoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ndnam198.tutsshoppinglist.presentation.ShoppingListView
import com.ndnam198.tutsshoppinglist.presentation.ShoppingListViewModel
import com.ndnam198.tutsshoppinglist.ui.theme.TutsShoppingListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TutsShoppingListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    ShoppingListView(ShoppingListViewModel())
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AppPreview() {
    TutsShoppingListTheme {
        ShoppingListView(ShoppingListViewModel())
    }
}