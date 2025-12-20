package com.navigation.live.moviesapp.presentation.movie_detail.intent

sealed class MovieDetailIntent {
    data class FetchMovieById(val id:String):MovieDetailIntent()
}