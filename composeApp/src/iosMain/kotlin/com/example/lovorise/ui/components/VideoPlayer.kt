package com.example.lovorise.ui.components

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import platform.AVFoundation.*
import platform.AVKit.*
import platform.Foundation.*
import platform.UIKit.*
import platform.CoreMedia.*
import kotlinx.cinterop.*

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun VideoPlayer(
    url: String,
    isVisible: Boolean,
    onProgress: (Float) -> Unit,
    modifier: Modifier
) {
    val nsUrl = NSURL.URLWithString(url) ?: return
    val player = remember(url) { AVPlayer(uRL = nsUrl).apply { 
        preventsDisplaySleepDuringVideoPlayback = true
    } }
    
    // Progress tracking
    DisposableEffect(player) {
        val interval = CMTimeMakeWithSeconds(0.05, 600)
        val observer = player.addPeriodicTimeObserverForInterval(interval, null) { time ->
            val duration = player.currentItem?.duration?.let { CMTimeGetSeconds(it) } ?: 0.0
            val current = CMTimeGetSeconds(time ?: return@addPeriodicTimeObserverForInterval)
            if (duration > 0) {
                onProgress((current / duration).toFloat())
            }
        }
        onDispose {
            player.removeTimeObserver(observer)
        }
    }

    UIKitView(
        factory = {
            val playerViewController = AVPlayerViewController()
            playerViewController.player = player
            playerViewController.showsPlaybackControls = false
            playerViewController.videoGravity = AVLayerVideoGravityResizeAspectFill
            
            // Loop video
            NSNotificationCenter.defaultCenter.addObserverForName(
                name = AVPlayerItemDidPlayToEndTimeNotification,
                `object` = player.currentItem,
                queue = null,
                usingBlock = {
                    player.seekToTime(CMTimeMake(value = 0, timescale = 1))
                    player.play()
                }
            )
            
            playerViewController.view
        },
        update = { view ->
            if (isVisible) {
                player.play()
            } else {
                player.pause()
            }
        },
        onRelease = {
            player.pause()
        },
        modifier = modifier
    )

    DisposableEffect(url) {
        onDispose {
            player.pause()
        }
    }
}
