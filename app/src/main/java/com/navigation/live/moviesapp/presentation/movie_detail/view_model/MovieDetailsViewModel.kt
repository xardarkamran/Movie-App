package com.navigation.live.moviesapp.presentation.movie_detail.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navigation.live.moviesapp.domain.repository.MoviesRepo
import com.navigation.live.moviesapp.presentation.movie_detail.intent.MovieDetailIntent
import com.navigation.live.moviesapp.presentation.movie_detail.state.MovieDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val moviesRepo: MoviesRepo
) : ViewModel() {

    private var _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState = _uiState.asStateFlow()

    private var fetchMovieJob: Job? = null

    fun handleIntent(movieDetailIntent: MovieDetailIntent) {
        when (movieDetailIntent) {
            is MovieDetailIntent.FetchMovieById -> fetchMovieById(movieDetailIntent.id)
        }
    }

    private fun fetchMovieById(id: String) {
        fetchMovieJob?.cancel() // Cancel previous job if exists
        fetchMovieJob = viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null // Clear previous error on new fetch
                )
            }
            moviesRepo.getMovieById(id)
                .onSuccess { movie ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            movie = movie,
                            error = null // Clear error on success
                        )
                    }
                }
                .onFailure { exception ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = exception.message
                                ?: "Unknown error occurred, please try again later"
                        )
                    }
                }
        }
    }


}