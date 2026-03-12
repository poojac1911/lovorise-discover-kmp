package com.example.lovorise.data.model

sealed class FeedItem {
    data class StoryItem(val story: Story) : FeedItem()
    data class ProfileItem(val profile: Profile) : FeedItem()
}

data class Story(
    val id: String,
    val images: List<String>,
    val author: Author,
    val caption: String,
    val timeAgo: String,
    val likes: String,
    val gifts: String,
    val shares: String,
    val comments: String
)

data class Profile(
    val id: String,
    val name: String,
    val age: Int,
    val location: String,
    val bio: String,
    val profileImage: String,
    val status: String = "Online",
    val distance: String = "2.5 km away"
)

data class Author(
    val id: String,
    val name: String,
    val avatar: String
)
