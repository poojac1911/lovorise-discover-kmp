package com.example.lovorise.ui.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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

@Composable
fun StoryItem(
    story: Story,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { story.images.size })

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Main Image Content
        if (story.images.size > 1) {
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
                    .padding(top = 80.dp, end = 20.dp) // Below tabs
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
        if (story.images.size > 1) {
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
