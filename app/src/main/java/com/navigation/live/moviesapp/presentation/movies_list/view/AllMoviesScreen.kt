package com.navigation.live.moviesapp.presentation.movies_list.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.navigation.live.moviesapp.domain.model.Movie
import com.navigation.live.moviesapp.presentation.movies_list.intent.MovieListIntent
import com.navigation.live.moviesapp.presentation.movies_list.view_model.AllMoviesViewModel
import com.navigation.live.moviesapp.presentation.shared.component.ShowRating
import com.navigation.live.moviesapp.presentation.shared.component.StatusBarIcons
import com.navigation.live.moviesapp.presentation.shared.utilz.MovieMockData

@Composable
fun AllMoviesScreen(
    allMoviesViewModel: AllMoviesViewModel = hiltViewModel(),
    onMovieDetail: (String) -> Unit
) {

    val uiState by allMoviesViewModel.uiState.collectAsState()

    // set up system bar text color
    StatusBarIcons(darkIcons = true)

    // Fetch movies only if list is empty and not loading
    LaunchedEffect(Unit) {
        if (uiState.list.isEmpty() && !uiState.isLoading) {
            allMoviesViewModel.handleIntent(MovieListIntent.FetchMoviesList)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .systemBarsPadding()
    ) {

        AppHeader()

        when {

            // Loading State
            uiState.isLoading -> {
                LoadingContent()
            }

            // Error State
            uiState.error != null -> {
                ErrorContent(uiState.error)
            }

            // Content State
            uiState.list.isNotEmpty() -> {
                ContentList(
                    uiState.list,
                    onMovieDetail = {
                        onMovieDetail(it)
                    }
                )
            }

            else -> {}

        }
    }
}

@Composable
fun AppHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Movie Hub",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun LoadingContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        CircularProgressIndicator(
            strokeWidth = 5.dp
        )
    }
}

@Composable
fun ContentList(
    list: List<Movie>,
    onMovieDetail: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn {
            items(list.size) {
                val model = list[it]
                MovieItem(model, onMovieDetail = {
                    onMovieDetail(model.id)
                })
            }
        }
    }
}

@Composable
fun ErrorContent(
    error: String?,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "$error")
    }
}

@Composable
fun MovieItem(
    movie: Movie,
    onMovieDetail: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 10.dp,
                vertical = 5.dp
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        onClick = {
            onMovieDetail(movie.id)
        }
    ) {
        Row {
            AsyncImage(
                model = movie.image,
                contentDescription = "Network Image",
                modifier = Modifier
                    .height(80.dp)
                    .width(130.dp)
                    .clip(RoundedCornerShape(7.dp)),
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .padding(start = 5.dp)
            ) {
                Text(
                    text = movie.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Text(
                    text = movie.description,
                    fontSize = 11.sp,
                    maxLines = 2,
                    lineHeight = 13.sp,
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(10.dp))
                ShowRating(movie.rtScore)
            }
        }
    }
}



@Preview
@Composable
fun PreviewShowLoading() {

    MovieItem(MovieMockData.mockMovie) {}
}