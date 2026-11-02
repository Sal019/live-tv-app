package com.salman.livetv.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.salman.livetv.data.sampleChannels
import com.salman.livetv.ui.screens.ChannelsScreen
import com.salman.livetv.ui.screens.FavouritesScreen
import com.salman.livetv.ui.screens.HomeScreen
import com.salman.livetv.ui.screens.PlayerScreen

// TV-only bottom navigation: no upload, no movies, no other sections.
sealed class BottomTab(val route: String, val label: String, val icon: ImageVector) {
    object Home : BottomTab("home", "Home", Icons.Default.Home)
    object Channels : BottomTab("channels", "Channels", Icons.Default.Tv)
    object Favourites : BottomTab("favourites", "Favourites", Icons.Default.Favorite)
    object Search : BottomTab("search", "Search", Icons.Default.Search)
}

private val tabs = listOf(BottomTab.Home, BottomTab.Channels, BottomTab.Favourites, BottomTab.Search)

@Composable
fun LiveTvNavGraph(navController: NavHostController = rememberNavController()) {
    val backStack by navController.currentBackStackEntryAsState()
    val currentRoute = backStack?.destination?.route
    val showBottomBar = currentRoute in tabs.map { it.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    tabs.forEach { tab ->
                        NavigationBarItem(
                            selected = currentRoute == tab.route,
                            onClick = {
                                navController.navigate(tab.route) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(tab.icon, contentDescription = tab.label) },
                            label = { Text(tab.label) }
                        )
                    }
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = BottomTab.Home.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(BottomTab.Home.route) {
                HomeScreen(
                    onChannelClick = { channel -> navController.navigate("player/${channel.id}") },
                    onSearchClick = { navController.navigate(BottomTab.Search.route) }
                )
            }
            composable(BottomTab.Channels.route) {
                ChannelsScreen(onChannelClick = { channel -> navController.navigate("player/${channel.id}") })
            }
            composable(BottomTab.Favourites.route) {
                // Favourites are empty until the heart action is wired to storage.
                FavouritesScreen(
                    favourites = emptyList(),
                    onChannelClick = { channel -> navController.navigate("player/${channel.id}") }
                )
            }
            composable(BottomTab.Search.route) {
                ChannelsScreen(onChannelClick = { channel -> navController.navigate("player/${channel.id}") })
            }
            composable("player/{channelId}") { entry ->
                val channelId = entry.arguments?.getString("channelId")
                val channel = sampleChannels.firstOrNull { it.id == channelId } ?: sampleChannels.first()
                PlayerScreen(
                    channel = channel,
                    onChannelClick = { next -> navController.navigate("player/${next.id}") }
                )
            }
        }
    }
}
