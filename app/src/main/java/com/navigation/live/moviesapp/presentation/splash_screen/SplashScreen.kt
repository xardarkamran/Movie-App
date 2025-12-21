package com.navigation.live.moviesapp.presentation.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.navigation.live.moviesapp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigate: () -> Unit
) {

    val durationMillis = 3000L
    var progress by remember { mutableFloatStateOf(0f) }
    LaunchedEffect(Unit) {

        val startTime = System.currentTimeMillis()
        val endTime = startTime + durationMillis

        // Animate progress from 0 to 100%
        while (System.currentTimeMillis() < endTime) {
            val elapsed = System.currentTimeMillis() - startTime
            progress = (elapsed.toFloat() / durationMillis.toFloat()).coerceIn(0f, 1f)
            delay(16) // Update ~60fps for smooth animation
        }
        // Ensure progress reaches 100%
        progress = 1f
        delay(100) // Small delay to show 100%
        onNavigate()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(R.drawable.app_icon),
                contentDescription = "Splash Icon",
                modifier = Modifier
                    .size(300.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(R.string.app_name),
                fontSize = 25.sp,
                color = Color.Black,
                lineHeight = 5.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 80.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .padding(
                        horizontal = 80.dp,
                    ),
                color = Color.Blue,
                trackColor = Color.Gray,
                strokeCap = StrokeCap.Butt
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen {}
}