package com.example.mindmatrix_aadk_labs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.ceil

@Composable
fun CalculativeAppScreen() {
    // 1. State Variables for User Input
    var amountInput by remember { mutableStateOf("") }
    var tipPercentage by remember { mutableStateOf(15f) }
    var roundUp by remember { mutableStateOf(false) }

    // 2. Calculation Logic tied directly to State
    // If the input is empty or invalid, it defaults to 0.0 to prevent crashes
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    var tip = amount * (tipPercentage / 100)

    if (roundUp) {
        tip = ceil(tip)
    }

    val total = amount + tip

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Tip Calculator UI",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 3. Number Input Field
            OutlinedTextField(
                value = amountInput,
                onValueChange = { amountInput = it },
                label = { Text("Bill Amount") },
                // This forces the number keyboard to open on the phone
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 4. Slider for Tip Percentage
            Text(text = "Tip Percentage: ${tipPercentage.toInt()}%")
            Slider(
                value = tipPercentage,
                onValueChange = { tipPercentage = it },
                valueRange = 0f..30f,
                steps = 5, // Snaps to 5%, 10%, 15%, etc.
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 5. Switch for Rounding Up
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Round up tip?")
                Switch(
                    checked = roundUp,
                    onCheckedChange = { roundUp = it }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 6. Dynamic Output Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Tip Amount: $${"%.2f".format(tip)}",
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Total Bill: $${"%.2f".format(total)}",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}