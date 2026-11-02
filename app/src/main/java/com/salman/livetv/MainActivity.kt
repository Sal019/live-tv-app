package com.salman.livetv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.salman.livetv.ui.navigation.LiveTvNavGraph
import com.salman.livetv.ui.theme.LiveTvTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiveTvTheme {
                LiveTvNavGraph()
            }
        }
    }
}
