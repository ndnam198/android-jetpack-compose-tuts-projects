package com.ndnam198.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ndnam198.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverterView("Android")
                }
            }
        }
    }
}

@Composable
fun UnitConverterView(name: String, modifier: Modifier = Modifier) {
    var inputExpanded by remember {
        mutableStateOf(false)
    }
    var outputExpanded by remember {
        mutableStateOf(false)
    }
    var inputUnit by remember {
        mutableStateOf("Centimeters")
    }
    var ouptutUnit by remember {
        mutableStateOf("Centimeters")
    }
    var inputValue by remember {
        mutableStateOf("")
    }

    LazyColumn() {
        item {  }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Text("Unit Converter")
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = inputValue,
            onValueChange = { inputValue = it },
            label = {
                Text("Enter value")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = { inputExpanded = true }) {
                Text(inputUnit)
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
            }
            DropdownMenu(expanded = inputExpanded, onDismissRequest = { inputExpanded = false }) {
                DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                    inputUnit = "Centimeters"
                    inputExpanded = false
                })
                DropdownMenuItem(text = { Text("Meters") }, onClick = {
                    inputUnit = "Meters"
                    inputExpanded = false
                })
                DropdownMenuItem(text = { Text("Feet") }, onClick = {
                    inputUnit = "Feet"
                    inputExpanded = false
                })
                DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                    inputUnit = "Millimeters"
                    inputExpanded = false
                })
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box(contentAlignment = Alignment.Center) {

                Button(onClick = { outputExpanded = true }) {
                    Text(ouptutUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = outputExpanded,
                    onDismissRequest = { outputExpanded = false }) {
                    DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                        ouptutUnit = "Centimeters"
                        outputExpanded = false
                    })
                    DropdownMenuItem(text = { Text("Meters") }, onClick = {
                        ouptutUnit = "Meters"
                        outputExpanded = false
                    })
                    DropdownMenuItem(text = { Text("Feet") }, onClick = {
                        ouptutUnit = "Feet"
                        outputExpanded = false
                    })
                    DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                        ouptutUnit = "Millimeters"
                        outputExpanded = false
                    })
                }
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: $inputValue")
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverterTheme {
        UnitConverterView("Android")
    }
}