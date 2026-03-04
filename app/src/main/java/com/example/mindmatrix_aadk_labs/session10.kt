package com.example.mindmatrix_aadk_labs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 1. Custom Data Class representing real-world data
data class ShoppingItem(
    val id: Int,
    val name: String,
    val originalPrice: Double,
    var currentPrice: Double = originalPrice
)

// 2. Singleton Object: Great for organizing helper functions and constants
object DiscountManager {
    const val HOLIDAY_DISCOUNT = 20.0 // 20% off

    fun calculateDiscountedPrice(price: Double, discountPercent: Double): Double {
        return price - (price * (discountPercent / 100))
    }
}

@Composable
fun ClassesAndLambdasScreen() {
    // Initializing our state with a list of our custom class objects
    var cartItems by remember {
        mutableStateOf(
            listOf(
                ShoppingItem(1, "Wireless Headphones", 150.0),
                ShoppingItem(2, "Mechanical Keyboard", 100.0),
                ShoppingItem(3, "Gaming Mouse", 60.0)
            )
        )
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Text(
                text = "Shopping Cart",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Learn Classes, Objects & Lambdas",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(24.dp))

            // We iterate through our items and create a card for each
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(cartItems) { item ->
                    ItemCard(
                        item = item,
                        // 3. LAMBDA IN ACTION: Passing a block of code to execute when clicked
                        onApplyDiscount = { clickedItem ->
                            // Map through the list to update the specific item's price
                            cartItems = cartItems.map {
                                if (it.id == clickedItem.id) {
                                    val newPrice = DiscountManager.calculateDiscountedPrice(
                                        it.originalPrice,
                                        DiscountManager.HOLIDAY_DISCOUNT
                                    )
                                    it.copy(currentPrice = newPrice)
                                } else {
                                    it
                                }
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // Another lambda to reset all prices back to normal
                    cartItems = cartItems.map { it.copy(currentPrice = it.originalPrice) }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Reset All Prices")
            }
        }
    }
}

// A reusable UI component that accepts a Lambda parameter: (ShoppingItem) -> Unit
@Composable
fun ItemCard(item: ShoppingItem, onApplyDiscount: (ShoppingItem) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = item.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Price: $${"%.2f".format(item.currentPrice)}",
                    fontSize = 16.sp,
                    color = if (item.currentPrice < item.originalPrice) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
            }

            Button(
                // We execute the lambda here, passing the current item back up to the parent!
                onClick = { onApplyDiscount(item) },
                enabled = item.currentPrice == item.originalPrice // Disables button after clicking once
            ) {
                Text("20% Off")
            }
        }
    }
}