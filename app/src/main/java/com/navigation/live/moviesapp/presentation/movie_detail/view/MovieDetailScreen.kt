package com.navigation.live.moviesapp.presentation.movie_detail.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.navigation.live.moviesapp.R
import com.navigation.live.moviesapp.domain.model.Movie
import com.navigation.live.moviesapp.presentation.movie_detail.intent.MovieDetailIntent
import com.navigation.live.moviesapp.presentation.movie_detail.view_model.MovieDetailsViewModel
import com.navigation.live.moviesapp.presentation.shared.component.ShowRating
import com.navigation.live.moviesapp.presentation.shared.component.StatusBarIcons
import com.navigation.live.moviesapp.presentation.shared.utilz.MovieMockData

@Composable
fun MovieDetailScreen(
    movieId: String,
    movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {

    val uiState by movieDetailsViewModel.uiState.collectAsState()

    // set up system bar text color
    StatusBarIcons(darkIcons = false)

    // Fetch movie when movieId changes
    LaunchedEffect(movieId) {
        movieDetailsViewModel.handleIntent(MovieDetailIntent.FetchMovieById(movieId))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp)
    ) {
        when {

            uiState.isLoading -> {
                LoadingContent()
            }

            uiState.movie != null -> {
                MovieContent(uiState.movie, onBackClick)
            }

            uiState.error != null -> {
                ContentError(uiState.error)
            }

        }
    }
}

@Composable
fun LoadingContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            strokeWidth = 5.dp
        )
    }
}

@Composable
fun ContentError(error: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = error ?: ""
        )
    }
}

@Composable
fun MovieContent(movie: Movie?, onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
        ) {
            AsyncImage(
                model = movie?.image,
                contentDescription = "Banner",
                modifier = Modifier.fillMaxSize(),
                placeholder = painterResource(R.drawable.poster),
                contentScale = ContentScale.FillBounds
            )
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(40.dp)
                    .background(
                        color = Color.Black.copy(alpha = 0.5f),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayCircle,
                    contentDescription = "Play video",
                    modifier = Modifier.size(50.dp),
                    tint = Color.White
                )
            }
        }

        AppHeader(onBackClick)
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 300.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(10.dp)
            ) {

                // Title
                Text(
                    text = "Movie",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Red
                )
                Text(
                    text = "${movie?.title}",
                    fontSize = 18.sp,
                    lineHeight = 12.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "${movie?.originalTitle}",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    lineHeight = 15.sp
                )

                // Movie Details
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    // Director
                    Column {
                        Text(
                            text = "Directed by",
                            fontSize = 12.sp,
                            color = Color.Gray,
                            lineHeight = 14.sp,
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = "${movie?.director}",
                            fontSize = 12.sp,
                            lineHeight = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Producer
                    Column {
                        Text(
                            text = "Producer by",
                            fontSize = 12.sp,
                            color = Color.Gray,
                            lineHeight = 14.sp,
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = "${movie?.producer}",
                            fontSize = 12.sp,
                            lineHeight = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Release Date
                    Column {
                        Text(
                            text = "Release Date",
                            fontSize = 12.sp,
                            color = Color.Gray,
                            lineHeight = 14.sp,
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = "${movie?.releaseDate}",
                            fontSize = 12.sp,
                            lineHeight = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }

                // Description
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Description",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "${movie?.description}",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    lineHeight = 15.sp
                )

                // Rating
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Rating",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
                ShowRating(movie?.rtScore ?: 0)
            }
        }
    }
}

@Composable
fun AppHeader(onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .statusBarsPadding()
            .padding(start = 10.dp, top = 15.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_back),
            contentDescription = "back button",
            modifier = Modifier
                .size(18.dp)
                .clickable {
                    onBackClick()
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMovieDetail() {
    MovieContent(MovieMockData.mockMovie) {}
}