package com.navigation.live.moviesapp.presentation.movie_detail.state

import com.navigation.live.moviesapp.domain.model.Movie

data class MovieDetailUiState(
    val isLoading: Boolean = false,
    val movie: Movie? = null,
    val error: String? = null
)