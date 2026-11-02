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

// Sample catalogue used for the UI only — swap in real licensed channels.
val sampleChannels = listOf(
    TvChannel("news1", "News 24", TvCategory.NEWS, "Prime Time News", Color(0xFFE50914)),
    TvChannel("news2", "Headline TV", TvCategory.NEWS, "Breaking: City Update", Color(0xFF2563EB)),
    TvChannel("sport1", "Sports HD", TvCategory.SPORTS, "Live: Football Match", Color(0xFF16A34A)),
    TvChannel("sport2", "Cricket Live", TvCategory.SPORTS, "Live: T20 Highlights", Color(0xFFD97706)),
    TvChannel("ent1", "Entertainment+", TvCategory.ENTERTAINMENT, "Evening Show", Color(0xFF9333EA)),
    TvChannel("ent2", "Drama One", TvCategory.ENTERTAINMENT, "Daily Serial — Ep. 42", Color(0xFFDB2777)),
    TvChannel("kids1", "Kids Zone", TvCategory.KIDS, "Cartoon Hour", Color(0xFF0891B2)),
    TvChannel("kids2", "Toon TV", TvCategory.KIDS, "Adventure Tales", Color(0xFFEA580C))
)

fun upNext(channel: TvChannel): List<Program> = listOf(
    Program(channel.nowPlaying, "Now"),
    Program("Evening Bulletin", "20:00"),
    Program("Night Special", "21:30")
)
