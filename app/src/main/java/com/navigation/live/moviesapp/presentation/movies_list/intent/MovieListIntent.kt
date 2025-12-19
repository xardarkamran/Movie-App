package com.navigation.live.moviesapp.presentation.movies_list.intent

sealed class MovieListIntent{
    data object FetchMoviesList : MovieListIntent()
}