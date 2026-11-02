package com.salman.livetv.data

import androidx.compose.ui.graphics.Color

enum class TvCategory { ALL, NEWS, SPORTS, ENTERTAINMENT, KIDS }

data class TvChannel(
    val id: String,
    val name: String,
    val category: TvCategory,
    val nowPlaying: String,
    val color: Color,
    // Demo stream placeholder. Replace with a licensed channel stream URL.
    val streamUrl: String = ""
)

data class Program(val title: String, val time: String)

// Demo videos only — Google's public sample videos, swapped out in a later
// update for real licensed channel streams. Nothing here is a real channel.
private const val DEMO_VIDEO_BASE =
    "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample"

val sampleChannels = listOf(
    TvChannel("news1", "News 24", TvCategory.NEWS, "Prime Time News", Color(0xFFE50914), "$DEMO_VIDEO_BASE/BigBuckBunny.mp4"),
    TvChannel("news2", "Headline TV", TvCategory.NEWS, "Breaking: City Update", Color(0xFF2563EB), "$DEMO_VIDEO_BASE/ForBiggerBlazes.mp4"),
    TvChannel("sport1", "Sports HD", TvCategory.SPORTS, "Live: Football Match", Color(0xFF16A34A), "$DEMO_VIDEO_BASE/ForBiggerEscapes.mp4"),
    TvChannel("sport2", "Cricket Live", TvCategory.SPORTS, "Live: T20 Highlights", Color(0xFFD97706), "$DEMO_VIDEO_BASE/ForBiggerJoyrides.mp4"),
    TvChannel("ent1", "Entertainment+", TvCategory.ENTERTAINMENT, "Evening Show", Color(0xFF9333EA), "$DEMO_VIDEO_BASE/ForBiggerMeltdowns.mp4"),
    TvChannel("ent2", "Drama One", TvCategory.ENTERTAINMENT, "Daily Serial — Ep. 42", Color(0xFFDB2777), "$DEMO_VIDEO_BASE/Sintel.mp4"),
    TvChannel("kids1", "Kids Zone", TvCategory.KIDS, "Cartoon Hour", Color(0xFF0891B2), "$DEMO_VIDEO_BASE/SubaruOutbackOnStreetAndDirt.mp4"),
    TvChannel("kids2", "Toon TV", TvCategory.KIDS, "Adventure Tales", Color(0xFFEA580C), "$DEMO_VIDEO_BASE/TearsOfSteel.mp4")
)

fun upNext(channel: TvChannel): List<Program> = listOf(
    Program(channel.nowPlaying, "Now"),
    Program("Evening Bulletin", "20:00"),
    Program("Night Special", "21:30")
)
