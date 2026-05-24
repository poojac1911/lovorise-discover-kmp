package com.example.lovorise.ui.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lovorise.ui.components.PremiumCircularProgressIndicator
import com.example.lovorise.data.mock.MockData
import com.example.lovorise.data.model.FeedItem
import com.example.lovorise.ui.components.SkeletonLoader
import com.example.lovorise.ui.theme.PinkAccent
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun DiscoverScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    var isLoadingInitial by remember { mutableStateOf(true) }
    var feedItems by remember { mutableStateOf(MockData.feedItems) }
    var isLoadingMore by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (isLoadingInitial) {
            delay(400) // Simulate cold start loading
            isLoadingInitial = false
        }
    }

    LaunchedEffect(selectedTab) {
        isLoadingMore = false
        isLoadingInitial = true
        delay(800)
        feedItems = if (selectedTab == 0) {
            MockData.feedItems
        } else {
            MockData.feedItems.filterIsInstance<FeedItem.ProfileItem>()
        }
        isLoadingInitial = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .navigationBarsPadding() // Fixed: Pager now respects navigation bar area
    ) {
        if (isLoadingInitial) {
            SkeletonLoader()
        } else {
            val pagerState = rememberPagerState(pageCount = { feedItems.size })

            VerticalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                beyondViewportPageCount = 1
            ) { index ->
                val item = feedItems[index]
                val isVisible = pagerState.currentPage == index
                Box(modifier = Modifier.fillMaxSize()) {
                    when (item) {
                        is FeedItem.StoryItem -> StoryItem(
                            story = item.story,
                            isVisible = isVisible,
                            modifier = Modifier.fillMaxSize()
                        )
                        is FeedItem.ProfileItem -> ProfileItem(
                            profile = item.profile,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }

            if (isLoadingMore) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.3f)),
                    contentAlignment = Alignment.Center
                ) {
                    PremiumCircularProgressIndicator(
                        size = 56.dp,
                        color = PinkAccent
                    )
                }
            }
            
            LaunchedEffect(pagerState.currentPage, selectedTab) {
                if (pagerState.currentPage >= feedItems.size - 2 && !isLoadingMore) {
                    isLoadingMore = true
                    delay(1000)
                    feedItems = feedItems + MockData.feedItems.shuffled()
                    isLoadingMore = false
                }
            }
        }

        FeedTabs(
            selectedTab = selectedTab,
            onTabSelected = { selectedTab = it },
            modifier = Modifier
                .statusBarsPadding()
                .align(Alignment.TopCenter)
        )

        BottomNavBar(
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
