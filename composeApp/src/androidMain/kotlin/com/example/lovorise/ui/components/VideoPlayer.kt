package com.example.lovorise.ui.components

import android.graphics.SurfaceTexture
import android.media.MediaPlayer
import android.view.Surface
import android.view.TextureView
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
actual fun VideoPlayer(
    url: String,
    isVisible: Boolean,
    onProgress: (Float) -> Unit,
    modifier: Modifier
) {
    val mediaPlayer = remember(url) { MediaPlayer() }
    var isPrepared by remember { mutableStateOf(false) }
    
    // Progress tracking
    LaunchedEffect(isPrepared, isVisible) {
        while (isActive) {
            if (isPrepared && mediaPlayer.isPlaying) {
                val duration = mediaPlayer.duration
                if (duration > 0) {
                    val progress = mediaPlayer.currentPosition.toFloat() / duration
                    onProgress(progress)
                }
            }
            delay(50) // Update every 50ms for smooth progress
        }
    }

    AndroidView(
        factory = { context ->
            TextureView(context).apply {
                surfaceTextureListener = object : TextureView.SurfaceTextureListener {
                    override fun onSurfaceTextureAvailable(st: SurfaceTexture, width: Int, height: Int) {
                        try {
                            val surface = Surface(st)
                            mediaPlayer.setSurface(surface)
                            mediaPlayer.reset()
                            mediaPlayer.setDataSource(url)
                            mediaPlayer.prepareAsync()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }

                    override fun onSurfaceTextureSizeChanged(st: SurfaceTexture, w: Int, h: Int) {}
                    
                    override fun onSurfaceTextureDestroyed(st: SurfaceTexture): Boolean {
                        try {
                            mediaPlayer.setSurface(null)
                        } catch (e: Exception) {
                        }
                        return true
                    }
                    
                    override fun onSurfaceTextureUpdated(st: SurfaceTexture) {}
                }
                
                mediaPlayer.setOnPreparedListener { mp ->
                    isPrepared = true
                    mp.isLooping = true
                    if (isVisible) mp.start()
                }
            }
        },
        update = { view ->
            try {
                if (isVisible && isPrepared) {
                    if (!mediaPlayer.isPlaying) mediaPlayer.start()
                } else if (isPrepared) {
                    if (mediaPlayer.isPlaying) mediaPlayer.pause()
                }
            } catch (e: Exception) {
            }
        },
        modifier = modifier
    )

    DisposableEffect(url) {
        onDispose {
            try {
                if (mediaPlayer.isPlaying) mediaPlayer.stop()
                mediaPlayer.release()
            } catch (e: Exception) {
            }
        }
    }
}
