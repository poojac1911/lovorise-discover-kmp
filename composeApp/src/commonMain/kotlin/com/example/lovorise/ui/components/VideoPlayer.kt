package com.example.lovorise.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun VideoPlayer(
    url: String,
    isVisible: Boolean,
    onProgress: (Float) -> Unit = {},
    modifier: Modifier = Modifier
)
