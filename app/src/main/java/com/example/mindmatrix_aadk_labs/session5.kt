package com.example.mindmatrix_aadk_labs



import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StateInComposeScreen() {
    // 1. Defining State: These variables remember their values and trigger UI updates when changed.
    var nameInput by remember { mutableStateOf("") }
    var clickCount by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "State & Recomposition",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 2. Text Input updating State instantly
            OutlinedTextField(
                value = nameInput,
                onValueChange = { newText ->
                    nameInput = newText // Updates state as you type
                },
                label = { Text("Enter your name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 3. Reacting to State changes
            val greeting = if (nameInput.isNotBlank()) "Hello, $nameInput!" else "Hello, Guest!"
            Text(
                text = greeting,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(48.dp))

            // 4. Button updating a different State variable
            Button(
                onClick = { clickCount++ }, // Increments count state
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text(text = "Clicked $clickCount times", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 5. Reset button to clear all states back to default
            OutlinedButton(
                onClick = {
                    nameInput = ""
                    clickCount = 0
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Reset Everything")
            }
        }
    }
}