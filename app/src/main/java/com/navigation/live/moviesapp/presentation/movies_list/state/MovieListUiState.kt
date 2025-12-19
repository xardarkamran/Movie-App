package com.navigation.live.moviesapp.presentation.movies_list.state

import com.navigation.live.moviesapp.domain.model.Movie

data class MovieListUiState(
    val isLoading: Boolean = false,
    val list: List<Movie> = emptyList(),
    val error: String? = null
)
