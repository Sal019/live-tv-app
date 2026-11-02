package com.salman.livetv.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.salman.livetv.data.TvChannel
import com.salman.livetv.data.sampleChannels
import com.salman.livetv.data.upNext
import com.salman.livetv.ui.components.ChannelLogo
import com.salman.livetv.ui.components.ChannelRowItem
import com.salman.livetv.ui.theme.TextSecondary

@Composable
fun PlayerScreen(channel: TvChannel, onChannelClick: (TvChannel) -> Unit) {
    val context = LocalContext.current
    val exoPlayer = remember(channel.id) {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(channel.streamUrl))
            prepare()
            playWhenReady = true
        }
    }
    DisposableEffect(Unit) { onDispose { exoPlayer.release() } }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            // Real demo video playback via Media3 ExoPlayer.
            AndroidView(
                factory = { ctx ->
                    PlayerView(ctx).apply {
                        player = exoPlayer
                        useController = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .background(Color.Black)
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ChannelLogo(channel)
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(channel.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Text(channel.nowPlaying, style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
                }
                IconButton(onClick = {}) { Icon(Icons.Default.FavoriteBorder, contentDescription = "Favourite") }
                IconButton(onClick = {}) { Icon(Icons.Default.Share, contentDescription = "Share") }
            }
            Text(
                "Up Next",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
        items(upNext(channel)) { program ->
            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(program.time, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.width(64.dp))
                Text(program.title, style = MaterialTheme.typography.bodyMedium)
            }
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                "More Channels",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        items(sampleChannels.filter { it.id != channel.id }) { other ->
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                ChannelRowItem(other, onClick = { onChannelClick(other) })
            }
        }
    }
}
