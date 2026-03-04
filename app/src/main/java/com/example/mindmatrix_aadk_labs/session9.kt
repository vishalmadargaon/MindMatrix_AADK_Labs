package com.example.mindmatrix_aadk_labs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LogicAndNullSafetyScreen() {
    // 1. State for user inputs
    var ageInput by remember { mutableStateOf("") }
    var promoCodeInput by remember { mutableStateOf("") }

    // State for the result message
    var resultMessage by remember { mutableStateOf("Please enter your details to check eligibility.") }
    var isError by remember { mutableStateOf(false) }

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
                text = "Eligibility Checker",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 2. Input Fields
            OutlinedTextField(
                value = ageInput,
                onValueChange = { ageInput = it },
                label = { Text("Age") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = promoCodeInput,
                onValueChange = { promoCodeInput = it },
                label = { Text("Promo Code (Optional)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 3. The "Verify" Button with our Logic & Null Safety
            Button(
                onClick = {
                    // NULL SAFETY: toIntOrNull() safely attempts to convert the string.
                    // If the user types text instead of a number, it returns 'null' instead of crashing!
                    val age: Int? = ageInput.toIntOrNull()

                    // NULL SAFETY: If the string is blank, treat it as a null promo code
                    val promo: String? = promoCodeInput.takeIf { it.isNotBlank() }

                    // CONDITIONALS: Logic flow to determine the result
                    if (age == null) {
                        isError = true
                        resultMessage = "Error: Please enter a valid number for your age."
                    } else if (age < 18) {
                        isError = true
                        resultMessage = "Sorry, you must be 18 or older to proceed."
                    } else {
                        isError = false
                        // NULL SAFETY: The Elvis Operator (?:) provides a default value if promo is null
                        val promoMessage = promo?.let { "using promo code '$it'" } ?: "without a promo code"
                        resultMessage = "Success! Access granted $promoMessage."
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Verify Eligibility")
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 4. Dynamic UI based on conditionals
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (isError) MaterialTheme.colorScheme.errorContainer
                    else MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = "Info",
                        tint = if (isError) MaterialTheme.colorScheme.error
                        else MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = resultMessage,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                        color = if (isError) MaterialTheme.colorScheme.onErrorContainer
                        else MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
        }
    }
}