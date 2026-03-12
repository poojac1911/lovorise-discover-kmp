package com.example.lovorise

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform