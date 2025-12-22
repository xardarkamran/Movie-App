package com.navigation.live.moviesapp.presentation.movie_detail.state

import com.navigation.live.moviesapp.domain.model.Movie

sealed class MovieDetailResult {
    data object Loading : MovieDetailResult()
    data class Success(val movie: Movie) : MovieDetailResult()
    data class Error(val error: String) : MovieDetailResult()
}