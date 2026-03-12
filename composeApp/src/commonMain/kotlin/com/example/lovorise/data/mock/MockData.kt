package com.example.lovorise.data.mock

import com.example.lovorise.data.model.Author
import com.example.lovorise.data.model.FeedItem
import com.example.lovorise.data.model.Profile
import com.example.lovorise.data.model.Story

object MockData {
    val stories = listOf(
        Story(
            id = "1",
            images = listOf("https://images.unsplash.com/photo-1494790108377-be9c29b29330?auto=format&fit=crop&q=80&w=1000"),
            author = Author("a1", "Kate Morrison", "https://i.pravatar.cc/150?u=kate"),
            caption = "Exploring the city lights tonight! ✨",
            timeAgo = "6 minutes ago",
            likes = "2.3k",
            gifts = "1.1k",
            shares = "20",
            comments = "15"
        ),
        Story(
            id = "2",
            images = listOf(
                "https://images.unsplash.com/photo-1544005313-94ddf0286df2?auto=format&fit=crop&q=80&w=1000",
                "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?auto=format&fit=crop&q=80&w=1000"
            ),
            author = Author("a2", "Liam Smith", "https://i.pravatar.cc/150?u=liam"),
            caption = "Work-life balance is key. 💼☕",
            timeAgo = "45 minutes ago",
            likes = "5.6k",
            gifts = "2.4k",
            shares = "45",
            comments = "30"
        ),
        Story(
            id = "3",
            images = listOf("https://images.unsplash.com/photo-1517841905240-472988babdf9?auto=format&fit=crop&q=80&w=1000"),
            author = Author("a3", "Emma Wilson", "https://i.pravatar.cc/150?u=emma"),
            caption = "Sunday brunch vibes. 🥞🥓",
            timeAgo = "2 hours ago",
            likes = "1.2k",
            gifts = "500",
            shares = "12",
            comments = "8"
        ),
        Story(
            id = "4",
            images = listOf(
                "https://images.unsplash.com/photo-1524504388940-b1c1722653e1?auto=format&fit=crop&q=80&w=1000",
                "https://images.unsplash.com/photo-1531746020798-e6953c6e8e04?auto=format&fit=crop&q=80&w=1000",
                "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?auto=format&fit=crop&q=80&w=1000"
            ),
            author = Author("a4", "Noah Garcia", "https://i.pravatar.cc/150?u=noah"),
            caption = "Road trip dump! 🚗💨 Swipe for more memories.",
            timeAgo = "5 hours ago",
            likes = "12.8k",
            gifts = "4.2k",
            shares = "120",
            comments = "85"
        ),
        Story(
            id = "5",
            images = listOf("https://images.unsplash.com/photo-1529626455594-4ff0802cfb7e?auto=format&fit=crop&q=80&w=1000"),
            author = Author("a5", "Sophia Chen", "https://i.pravatar.cc/150?u=sophia"),
            caption = "Finally visited the Grand Canyon! Breathtaking view. 😍",
            timeAgo = "1 day ago",
            likes = "7.4k",
            gifts = "1.8k",
            shares = "60",
            comments = "42"
        )
    )

    val profiles = listOf(
        Profile(
            id = "p1",
            name = "John Parker",
            age = 26,
            location = "Canada, Toronto",
            bio = "I love traveling and exploring new cultures. Seeking an adventurous soul!",
            profileImage = "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?auto=format&fit=crop&q=80&w=1000"
        ),
        Profile(
            id = "p2",
            name = "Sarah Miller",
            age = 24,
            location = "USA, New York",
            bio = "Coffee lover and tech enthusiast. Let's talk about the future of AI!",
            profileImage = "https://images.unsplash.com/photo-1494790108377-be9c29b29330?auto=format&fit=crop&q=80&w=1000"
        ),
        Profile(
            id = "p3",
            name = "Emily Davis",
            age = 22,
            location = "London, UK",
            bio = "Design student by day, gamer by night. 🎨🎮",
            profileImage = "https://images.unsplash.com/photo-1524504388940-b1c1722653e1?auto=format&fit=crop&q=80&w=1000"
        ),
        Profile(
            id = "p4",
            name = "Michael Brown",
            age = 28,
            location = "Sydney, Australia",
            bio = "Professional surfer and fitness coach. Catch me at the beach! 🏄‍♂️",
            profileImage = "https://images.unsplash.com/photo-1492562080023-ab3dbdf5bf3d?auto=format&fit=crop&q=80&w=1000"
        ),
        Profile(
            id = "p5",
            name = "Olivia Martinez",
            age = 25,
            location = "Barcelona, Spain",
            bio = "Chef and foodie. I can make the best tapas you've ever had. 🍳",
            profileImage = "https://images.unsplash.com/photo-1534528741775-53994a69daeb?auto=format&fit=crop&q=80&w=1000"
        ),
        Profile(
            id = "p6",
            name = "William Taylor",
            age = 27,
            location = "Berlin, Germany",
            bio = "Jazz musician and vinyl collector. Music is life. 🎷",
            profileImage = "https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?auto=format&fit=crop&q=80&w=1000"
        ),
        Profile(
            id = "p7",
            name = "Ava Anderson",
            age = 23,
            location = "Paris, France",
            bio = "Photographer capturing the beauty of mundane life. 📷",
            profileImage = "https://images.unsplash.com/photo-1517841905240-472988babdf9?auto=format&fit=crop&q=80&w=1000"
        ),
        Profile(
            id = "p8",
            name = "James Wilson",
            age = 30,
            location = "Chicago, USA",
            bio = "Software Engineer. I building apps and drinking dark roasted coffee. 💻☕",
            profileImage = "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?auto=format&fit=crop&q=80&w=1000"
        )
    )

    val feedItems = listOf(
        FeedItem.StoryItem(stories[0]),
        FeedItem.ProfileItem(profiles[0]),
        FeedItem.StoryItem(stories[1]),
        FeedItem.ProfileItem(profiles[1]),
        FeedItem.StoryItem(stories[2]),
        FeedItem.ProfileItem(profiles[2]),
        FeedItem.StoryItem(stories[3]),
        FeedItem.ProfileItem(profiles[3]),
        FeedItem.ProfileItem(profiles[4]),
        FeedItem.StoryItem(stories[4]),
        FeedItem.ProfileItem(profiles[5]),
        FeedItem.ProfileItem(profiles[6]),
        FeedItem.ProfileItem(profiles[7])
    )
}
