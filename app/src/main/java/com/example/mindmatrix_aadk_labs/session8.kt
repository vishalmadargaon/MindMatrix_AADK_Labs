package com.example.mindmatrix_aadk_labs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Star // <-- Changed to Star!
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 1. Data class for our gallery items
data class Artwork(val title: String, val artist: String, val year: String, val color: Color)

@Composable
fun ArtNavigationScreen() {
    // 2. Our List of Artworks
    val artworks = listOf(
        Artwork("Mona Lisa", "Leonardo da Vinci", "1503", Color(0xFF8D6E63)),
        Artwork("Starry Night", "Vincent van Gogh", "1889", Color(0xFF1E88E5)),
        Artwork("The Persistence of Memory", "Salvador Dalí", "1931", Color(0xFFFFB300)),
        Artwork("Girl with a Pearl Earring", "Johannes Vermeer", "1665", Color(0xFF26A69A))
    )

    // 3. State: Tracking which artwork we are currently looking at
    var currentIndex by remember { mutableStateOf(0) }
    val currentArt = artworks[currentIndex]

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

            // 4. The "Artwork" Display Frame
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(currentArt.color, shape = MaterialTheme.shapes.large)
                    .padding(16.dp)
                    .testTag("ArtworkImage"),
                contentAlignment = Alignment.Center
            ) {
                // Changed from Palette to Star to fix the error!
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    modifier = Modifier.size(100.dp),
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 5. Artwork Details
            Text(
                text = currentArt.title,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.testTag("ArtworkTitle")
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${currentArt.artist} (${currentArt.year})",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.testTag("ArtworkArtist")
            )

            Spacer(modifier = Modifier.height(48.dp))

            // 6. Navigation Buttons Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        currentIndex = if (currentIndex == 0) artworks.size - 1 else currentIndex - 1
                    },
                    modifier = Modifier.testTag("PreviousButton")
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Previous")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Previous")
                }

                Button(
                    onClick = {
                        currentIndex = (currentIndex + 1) % artworks.size
                    },
                    modifier = Modifier.testTag("NextButton")
                ) {
                    Text("Next")
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Filled.ArrowForward, contentDescription = "Next")
                }
            }
        }
    }
}