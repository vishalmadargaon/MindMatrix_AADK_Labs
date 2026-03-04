package com.example.mindmatrix_aadk_labs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

// 1. Data class for our items
data class Task(val id: Int, val name: String, var isCompleted: Boolean = false)

@Composable
fun DeepDiveStateScreen() {
    // 2. Advanced State: Using mutableStateListOf to track a collection of items
    val tasks = remember {
        mutableStateListOf(
            Task(1, "Design UI Mockups"),
            Task(2, "Build Jetpack Compose Layouts"),
            Task(3, "Implement State Management"),
            Task(4, "Test Application Logic")
        )
    }

    // 3. Derived State: Calculating progress dynamically when the list changes
    val completedTasks = tasks.count { it.isCompleted }
    val totalTasks = tasks.size
    val progress = if (totalTasks > 0) completedTasks.toFloat() / totalTasks else 0f

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "Project Milestones",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 4. UI instantly reacts to Derived State
        Text(text = "Progress: $completedTasks / $totalTasks completed")
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 5. Rendering our stateful list
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(tasks) { task ->
                TaskRow(
                    task = task,
                    onTaskToggled = { isChecked ->
                        // Update the specific task to trigger a recomposition
                        val index = tasks.indexOf(task)
                        if (index != -1) {
                            tasks[index] = tasks[index].copy(isCompleted = isChecked)
                        }
                    }
                )
            }
        }

        Button(
            onClick = {
                // Resetting all items in the list
                for (i in tasks.indices) {
                    tasks[i] = tasks[i].copy(isCompleted = false)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reset Milestones")
        }
    }
}

// 6. State Hoisting: Passing the state and the action down to a child component
@Composable
fun TaskRow(task: Task, onTaskToggled: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = task.isCompleted,
            onCheckedChange = { onTaskToggled(it) }
        )
        Text(
            text = task.name,
            style = MaterialTheme.typography.bodyLarge,
            // Visually show completion by striking through the text
            textDecoration = if (task.isCompleted) TextDecoration.LineThrough else null,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}