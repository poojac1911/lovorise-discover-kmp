package com.example.lovorise.ui.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lovorise.ui.components.LovoriseText
import com.example.lovorise.ui.components.LovoriseScrim
import coil3.compose.AsyncImage
import com.example.lovorise.data.model.Story
import com.example.lovorise.ui.theme.PinkAccent
import com.example.lovorise.ui.components.PremiumCircularProgressIndicator
import com.example.lovorise.ui.components.VideoPlayer

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun StoryItem(
    story: Story,
    isVisible: Boolean,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { story.images.size })

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Main Content (Video or Image Pager)
        if (story.videoUrl != null) {
            var videoProgress by remember { mutableStateOf(0f) }
            
            Box(Modifier.fillMaxSize()) {
                // Background Image/Fallback
                coil3.compose.SubcomposeAsyncImage(
                    model = story.images.firstOrNull(),
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
                
                VideoPlayer(
                    url = story.videoUrl,
                    isVisible = isVisible,
                    onProgress = { videoProgress = it },
                    modifier = Modifier
                )

                // Video Progress Bar (Bottom)
                LinearWavyProgressIndicator(
                    progress = { videoProgress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 4.dp)
                        .padding(bottom = 60.dp) // Positioned just above the BottomNavBar/Indicators area
                        .height(3.dp)
                        .clip(RoundedCornerShape(1.dp)),
                    color = PinkAccent,
                    trackColor = Color.White,
                )
            }
        } else if (story.images.size > 1) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                coil3.compose.SubcomposeAsyncImage(
                    model = story.images[page],
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
            }
            
            // Pagination Indicator (e.g., 2/6) - Positioned below the tabs
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 100.dp, end = 20.dp) // Below tabs
                    .background(Color.Black.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                LovoriseText(
                    text = "${pagerState.currentPage + 1} / ${story.images.size}",
                    fontSize = 13,
                    fontWeight = FontWeight.Medium
                )
            }
        } else {
            coil3.compose.SubcomposeAsyncImage(
                model = story.images[0],
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
        }

        // Gradient Overlay
        LovoriseScrim(startY = 500f)

        // Sidebar Actions
        ActionSidebar(
            likes = story.likes,
            comments = story.comments,
            gifts = story.gifts,
            shares = story.shares,
            authorAvatar = story.author.avatar,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 100.dp, end = 12.dp)
        )

        // Bottom Content (Author, Caption)
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, bottom = 120.dp, end = 80.dp) // Above nav bar & cleared from sidebar
        ) {
            LovoriseText(
                text = story.author.name,
                fontWeight = FontWeight.Bold,
                fontSize = 18
            )
            LovoriseText(
                text = story.caption,
                fontSize = 15,
                modifier = Modifier.padding(top = 6.dp),
                maxLines = 2
            )
            LovoriseText(
                text = story.timeAgo,
                alpha = 0.6f,
                fontSize = 12,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        
        // Pager Indicators (dots)
        if (story.videoUrl == null && story.images.size > 1) {
            Row(
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 90.dp), // Positioned above the nav bar center
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(story.images.size) { iteration ->
                    val isSelected = pagerState.currentPage == iteration
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 3.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) Color.White else Color.White.copy(alpha = 0.3f))
                            .size(if (isSelected) 7.dp else 5.dp)
                    )
                }
            }
        }
    }
}
