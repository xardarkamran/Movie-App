package com.navigation.live.moviesapp.presentation.movies_list.state

import com.navigation.live.moviesapp.domain.model.Movie

sealed class MovieListResult {
    data object Loading : MovieListResult()
    data class Success(val list: List<Movie>) : MovieListResult()
    data class Error(val error: String) : MovieListResult()
}
