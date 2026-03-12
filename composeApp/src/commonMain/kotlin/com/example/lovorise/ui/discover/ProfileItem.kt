package com.example.lovorise.ui.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lovorise.ui.components.LovoriseIcon
import com.example.lovorise.ui.components.LovoriseIconButton
import com.example.lovorise.ui.components.LovoriseText
import com.example.lovorise.ui.components.LovoriseScrim
import com.example.lovorise.data.model.Profile
import com.example.lovorise.ui.theme.PinkAccent
import com.example.lovorise.ui.components.PremiumCircularProgressIndicator

@Composable
fun ProfileItem(
    profile: Profile,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Main Image
        coil3.compose.SubcomposeAsyncImage(
            model = profile.profileImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            loading = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    PremiumCircularProgressIndicator(
                        size = 40.dp,
                        color = PinkAccent
                    )
                }
            }
        )

        // Gradient Overlay
        LovoriseScrim(startY = 300f)

        // Sidebar Actions
        ActionSidebar(
            likes = "2.3k",
            comments = "15",
            gifts = "1.1k",
            shares = "20",
            authorAvatar = profile.profileImage,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 100.dp, end = 12.dp)
        )

        // Bottom Content (Profile info)
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, bottom = 120.dp, end = 80.dp)
        ) {
            LovoriseText(
                text = profile.name,
                fontWeight = FontWeight.Bold,
                fontSize = 22
            )
            
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp)
            ) {
                LovoriseIcon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.7f),
                    size = 14.dp
                )
                LovoriseText(
                    text = "${profile.age} years",
                    alpha = 0.9f,
                    fontSize = 14,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 2.dp)
            ) {
                LovoriseIcon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.7f),
                    size = 14.dp
                )
                LovoriseText(
                    text = profile.location,
                    alpha = 0.9f,
                    fontSize = 14,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            LovoriseText(
                text = profile.bio,
                alpha = 0.8f,
                fontSize = 14,
                modifier = Modifier.padding(top = 8.dp),
                maxLines = 2
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            // Audio Visualizer Bar
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(20.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                LovoriseIcon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play",
                    size = 24.dp
                )
                
                Spacer(modifier = Modifier.width(8.dp))
                
                // Simple dummy visualizer waves
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(20) { index ->
                        val height = (5..15).random().dp
                        Box(
                            modifier = Modifier
                                .width(2.dp)
                                .height(height)
                                .background(Color.White, RoundedCornerShape(1.dp))
                        )
                    }
                }
            }
        }
        
        // Return button (top left)
        LovoriseIconButton(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            onClick = { },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
        )
    }
}
