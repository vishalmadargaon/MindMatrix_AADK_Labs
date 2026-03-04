package com.example.mindmatrix_aadk_labs

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 1. THE VIEWMODEL ARCHITECTURE (No external Gradle dependencies needed!)
// We separated the logic completely from the UI code.
class CounterViewModel {
    private val _count = mutableIntStateOf(0)

    val count: Int
        get() = _count.intValue

    fun increment() {
        _count.intValue++
    }

    fun decrement() {
        if (_count.intValue > 0) {
            _count.intValue--
        }
    }

    fun reset() {
        _count.intValue = 0
    }
}

// 2. THE UI
@Composable
fun ArchitectureScreen() {
    // Instantiating our ViewModel pattern using remember to avoid unresolved imports
    val viewModel = remember { CounterViewModel() }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "ViewModel Architecture",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Separation of Concerns in Action",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Reading the state directly from the ViewModel
            Text(
                text = "${viewModel.count}",
                fontSize = 80.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.height(48.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Sending events to the ViewModel instead of calculating here
                Button(
                    onClick = { viewModel.decrement() },
                    modifier = Modifier.size(64.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text("-", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = { viewModel.increment() },
                    modifier = Modifier.size(64.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("+", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedButton(onClick = { viewModel.reset() }) {
                Text("Reset Counter")
            }
        }
    }
}