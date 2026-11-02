package com.salman.livetv.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.salman.livetv.data.TvCategory
import com.salman.livetv.data.TvChannel
import com.salman.livetv.data.sampleChannels
import com.salman.livetv.ui.components.ChannelCard

@Composable
fun ChannelsScreen(onChannelClick: (TvChannel) -> Unit) {
    var query by remember { mutableStateOf("") }
    var category by remember { mutableStateOf(TvCategory.ALL) }
    val filtered = sampleChannels.filter {
        (category == TvCategory.ALL || it.category == category) &&
            (query.isBlank() || it.name.contains(query, ignoreCase = true))
    }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Search channels") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(TvCategory.values().toList()) { cat ->
                FilterChip(
                    selected = category == cat,
                    onClick = { category = cat },
                    label = { Text(cat.name.lowercase().replaceFirstChar { c -> c.uppercase() }) }
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(filtered) { channel ->
                ChannelCard(channel, onClick = { onChannelClick(channel) })
            }
        }
    }
}

@Composable
fun FavouritesScreen(favourites: List<TvChannel>, onChannelClick: (TvChannel) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("My Favourites", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))
        if (favourites.isEmpty()) {
            Text(
                "No favourite channels yet. Tap the heart on a channel to save it here.",
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(favourites) { channel ->
                    ChannelCard(channel, onClick = { onChannelClick(channel) })
                }
            }
        }
    }
}
