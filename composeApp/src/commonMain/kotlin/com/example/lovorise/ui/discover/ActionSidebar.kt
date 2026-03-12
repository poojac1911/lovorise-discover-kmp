package com.example.lovorise.ui.discover

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.lovorise.ui.components.LovoriseText
import com.example.lovorise.ui.components.LovoriseIcon
import com.example.lovorise.ui.components.LovoriseIconButton
import com.example.lovorise.ui.theme.PinkAccent

@Composable
fun ActionSidebar(
    likes: String,
    comments: String,
    gifts: String,
    shares: String,
    authorAvatar: String? = null,
    modifier: Modifier = Modifier
) {
    var isLiked by remember { mutableStateOf(false) }
    var likedCount by remember { mutableStateOf(likes) }

    Column(
        modifier = modifier.padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Author Avatar with Plus Button
        if (authorAvatar != null) {
            Box(
                modifier = Modifier.padding(bottom = 8.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .background(Color.White, CircleShape)
                        .padding(1.dp)
                ) {
                    AsyncImage(
                        model = authorAvatar,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                    )
                }
                
                // Pulsing Plus Button
                Box(
                    modifier = Modifier
                        .offset(y = 8.dp)
                        .size(18.dp)
                        .clip(CircleShape)
                        .background(PinkAccent)
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    LovoriseIcon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        size = 12.dp
                    )
                }
            }
        }

        ActionButton(icon = Icons.Default.ChatBubbleOutline, count = comments)
        ActionButton(
            icon = Icons.Default.FavoriteBorder, 
            count = likedCount,
            isActive = isLiked,
            onClick = { 
                isLiked = !isLiked
                likedCount = if (isLiked) "2.4k" else likes
            }
        )
        ActionButton(icon = Icons.Default.CardGiftcard, count = gifts)
        ActionButton(icon = Icons.Default.Share, count = shares)
        
        // More options button (dots)
        LovoriseIconButton(
            imageVector = Icons.Default.MoreHoriz,
            contentDescription = "More",
            onClick = { },
            size = 24.dp
        )
    }
}

@Composable
fun ActionButton(
    icon: ImageVector,
    count: String,
    isActive: Boolean = false,
    activeColor: Color = PinkAccent,
    onClick: () -> Unit = {}
) {
    val scale by animateFloatAsState(
        targetValue = if (isActive) 1.3f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LovoriseIconButton(
            imageVector = if (isActive && icon == Icons.Default.FavoriteBorder) Icons.Default.Favorite else icon,
            contentDescription = null,
            tint = if (isActive) activeColor else Color.White,
            size = 28.dp,
            onClick = onClick,
            modifier = Modifier.graphicsLayer(scaleX = scale, scaleY = scale)
        )
        LovoriseText(
            text = count,
            fontSize = 12,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}
