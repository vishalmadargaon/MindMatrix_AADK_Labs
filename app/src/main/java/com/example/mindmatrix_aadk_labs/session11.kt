package com.example.mindmatrix_aadk_labs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 1. Data Modeling: Creating a blueprint for our list items
data class ZooAnimal(
    val name: String,
    val species: String,
    val diet: String,
    val icon: ImageVector
)

@Composable
fun LazyColumnListScreen() {
    // 2. Creating a list of our data objects using Core Icons
    val animalList = listOf(
        ZooAnimal("Simba", "African Lion", "Carnivore", Icons.Filled.Star),
        ZooAnimal("Dumbo", "African Elephant", "Herbivore", Icons.Filled.Favorite),
        ZooAnimal("Kaa", "Python", "Carnivore", Icons.Filled.Face),
        ZooAnimal("Melman", "Giraffe", "Herbivore", Icons.Filled.Favorite),
        ZooAnimal("Marty", "Zebra", "Herbivore", Icons.Filled.Star),
        ZooAnimal("Gloria", "Hippopotamus", "Herbivore", Icons.Filled.Favorite),
        ZooAnimal("Skipper", "Penguin", "Carnivore", Icons.Filled.Face)
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Zoo Directory",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 16.dp, top = 16.dp)
            )

            // 3. LazyColumn: The Compose way to handle scrollable lists efficiently
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp) // Adds space between each card
            ) {
                // The 'items' function iterates through our list automatically
                items(animalList) { animal ->
                    AnimalCard(animal = animal)
                }
            }
        }
    }
}

// 4. Reusable UI Component representing a single row in the list
@Composable
fun AnimalCard(animal: ZooAnimal) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = animal.icon,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = animal.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = animal.species,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Diet: ${animal.diet}",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}