package com.salman.livetv.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.salman.livetv.data.TvChannel
import com.salman.livetv.data.sampleChannels
import com.salman.livetv.ui.components.ChannelCard
import com.salman.livetv.ui.components.ChannelLogo
import com.salman.livetv.ui.components.LiveBadge
import com.salman.livetv.ui.theme.AccentViolet

@Composable
fun HomeScreen(onChannelClick: (TvChannel) -> Unit, onSearchClick: () -> Unit) {
    val featured = sampleChannels.first()
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Live TV", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.weight(1f))
            Icon(Icons.Default.Search, contentDescription = "Search")
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Featured LIVE banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(AccentViolet)
                .padding(16.dp)
        ) {
            Column {
                LiveBadge()
                Spacer(modifier = Modifier.height(12.dp))
                Text(featured.name, style = MaterialTheme.typography.headlineMedium)
                Text(featured.nowPlaying, style = MaterialTheme.typography.bodyMedium)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text("Live Channels", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(sampleChannels) { channel ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    ChannelLogo(channel)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(channel.name, style = MaterialTheme.typography.labelMedium)
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text("All Channels", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(sampleChannels) { channel ->
                ChannelCard(channel, onClick = { onChannelClick(channel) })
            }
        }
    }
}
