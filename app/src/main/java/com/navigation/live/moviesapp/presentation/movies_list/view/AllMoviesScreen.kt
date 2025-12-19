package com.navigation.live.moviesapp.presentation.movies_list.view

import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
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

private const val TAG = "AllMoviesScreen"

@Composable
fun AllMoviesScreen(
    allMoviesViewModel: AllMoviesViewModel = hiltViewModel(),
    onMovieDetail: () -> Unit
) {

    val uiState by allMoviesViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        allMoviesViewModel.handleIntent(MovieListIntent.FetchMoviesList)
    }

    LaunchedEffect(uiState.list) {
        if (uiState.list.isNotEmpty()) {
            Log.d(TAG, "AllMoviesScreen:movies list -> ${uiState.list}")
            Log.d(TAG, "AllMoviesScreen:movies list size -> ${uiState.list.size}")
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
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
                    onMovieDetail
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
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            strokeWidth = 5.dp
        )
    }
}

@Composable
fun ContentList(
    list: List<Movie>,
    onMovieDetail: () -> Unit
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
                MovieItem(model, onMovieDetail)
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
    onMovieDetail: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 10.dp,
                vertical = 10.dp
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        onClick = onMovieDetail
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
                    .padding(start = 10.dp)
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

@Composable
fun ShowRating(ratingScore: Int) {
    val stars = ((ratingScore * 5) / 100).coerceIn(0, 5)
    Row {
        repeat(5) { index ->
            Icon(
                imageVector = if (index < stars) Icons.Default.Star else Icons.Outlined.Star,
                contentDescription = "Rating",
                tint = if (index < stars) {
                    Color(0xFFFFC107)
                } else {
                    Color.Gray
                },
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewShowLoading() {
    val mockMovie = Movie(
        id = "2baf70d1-42bb-4437-b551-e5fed5a87abe",
        title = "Castle in the Sky",
        originalTitle = "天空の城ラピュタ",
        originalTitleRomanised = "Tenkū no shiro Rapyuta",
        image = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/npOnzAbLh6VOIu3naU5QaEcTepo.jpg",
        movieBanner = "https://image.tmdb.org/t/p/w533_and_h300_bestv2/3cyjYtLWCBE1uvWINHFsFnE8LUK.jpg",
        description = "The orphan Sheeta inherited a mysterious crystal that links her to the mythical sky-kingdom of Laputa. With the help of resourceful Pazu and a rollicking band of sky pirates, she makes her way to the ruins of the once-great civilization.",
        director = "Hayao Miyazaki",
        producer = "Isao Takahata",
        releaseDate = "1986",
        runningTime = 124,
        rtScore = 95,
        people = listOf(
            "https://ghibliapi.vercel.app/people/598f7048-74ff-41e0-92ef-87dc1ad980a9"
        ),
        species = listOf(
            "https://ghibliapi.vercel.app/species/af3910a6-429f-4c74-9ad5-dfe1c4aa04f2"
        ),
        locations = listOf(
            "https://ghibliapi.vercel.app/locations/"
        ),
        vehicles = listOf(
            "https://ghibliapi.vercel.app/vehicles/4e09b023-f650-4747-9ab9-eacf14540cfb"
        ),
        url = "https://ghibliapi.vercel.app/films/2baf70d1-42bb-4437-b551-e5fed5a87abe"
    )
    MovieItem(mockMovie) {}
}