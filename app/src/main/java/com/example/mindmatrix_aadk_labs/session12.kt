package com.example.mindmatrix_aadk_labs

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ThemingAndAnimationScreen() {
    // 1. State: Tracks whether our card is expanded or collapsed
    var isExpanded by remember { mutableStateOf(false) }

    // 2. UI Animation: Smoothly transitions the height
    val cardHeight by animateDpAsState(
        targetValue = if (isExpanded) 250.dp else 120.dp,
        animationSpec = tween(durationMillis = 500), // Takes half a second to animate
        label = "heightAnimation"
    )

    // 3. UI Animation: Smoothly transitions the background color
    val cardColor by animateColorAsState(
        targetValue = if (isExpanded) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer,
        animationSpec = tween(durationMillis = 500),
        label = "colorAnimation"
    )

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
                text = "Compose Animations",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 4. The Animated Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardHeight) // Uses the animated height state!
                    .clip(RoundedCornerShape(16.dp)) // Material Shape
                    .background(cardColor) // Uses the animated color state!
                    .clickable { isExpanded = !isExpanded } // Toggles state on click
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Favorite",
                        tint = if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(40.dp)
                    )

                    // Conditionally showing text based on the animation state
                    if (isExpanded) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Material Theming makes it easy to keep colors consistent, and Compose Animations make UI state changes feel incredibly smooth!",
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp
                        )
                    } else {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Tap to Expand",
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}