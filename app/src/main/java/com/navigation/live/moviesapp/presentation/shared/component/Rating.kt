package com.navigation.live.moviesapp.presentation.shared.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShowRating(ratingScore: Int) {
    val stars = ((ratingScore * 5) / 100).coerceIn(0, 5)
    Row {
        repeat(5) { index ->
            Icon(
                imageVector = if (index < stars)
                    Icons.Default.Star
                else
                    Icons.Default.StarBorder,
                contentDescription = "Rating",
                tint = if (index < stars) {
                    Color(0xFFFFC107)
                } else {
                    Color(0xFFFFC107)
                },
                modifier = Modifier.size(14.dp)
            )
        }
    }
}