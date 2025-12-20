package com.navigation.live.moviesapp.presentation.shared.component

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat

@Composable
fun StatusBarIcons(
    darkIcons: Boolean
) {
    val context = LocalContext.current
    val activity = context as? ComponentActivity

    // Set status bar to light icons (white text)
    SideEffect {
        activity?.window?.let { window ->
            WindowCompat.getInsetsController(window, window.decorView).apply {
                isAppearanceLightStatusBars = darkIcons // false = light icons (white)
            }
        }
    }


}
