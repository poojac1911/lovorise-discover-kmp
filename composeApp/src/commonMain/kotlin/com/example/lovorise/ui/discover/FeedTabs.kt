package com.example.lovorise.ui.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tune
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lovorise.ui.components.LovoriseIconButton
import com.example.lovorise.ui.components.LovoriseText

@Composable
fun FeedTabs(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            TabText(
                text = "For you",
                isSelected = selectedTab == 0,
                onClick = { onTabSelected(0) }
            )
            TabText(
                text = "Connections",
                isSelected = selectedTab == 1,
                onClick = { onTabSelected(1) }
            )
        }
        
        LovoriseIconButton(
            imageVector = Icons.Default.Tune,
            contentDescription = "Filter",
            onClick = { },
            size = 24.dp
        )
    }
}

@Composable
fun TabText(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        LovoriseText(
            text = text,
            alpha = if (isSelected) 1f else 0.5f,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            fontSize = 17
        )
        if (isSelected) {
            Box(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .width(20.dp)
                    .height(2.dp)
                    .background(Color.White)
            )
        } else {
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}
