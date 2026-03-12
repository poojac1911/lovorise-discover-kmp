package com.example.lovorise.ui.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.lovorise.ui.theme.PinkAccent

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color.Black,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavIcon(Icons.Filled.Style, isSelected = true) // Home/Discover
            NavIcon(Icons.Outlined.LocationOn)
            NavIcon(Icons.Outlined.AddCircleOutline, isSpecial = true)
            NavIcon(Icons.Outlined.ChatBubbleOutline)
            NavIcon(Icons.Outlined.Person)
        }
    }
}

@Composable
fun NavIcon(
    icon: ImageVector,
    isSelected: Boolean = false,
    isSpecial: Boolean = false
) {
    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = if (isSelected) PinkAccent else if (isSpecial) Color.White else Color.Gray,
        modifier = Modifier.size(if (isSpecial) 32.dp else 28.dp)
    )
}
