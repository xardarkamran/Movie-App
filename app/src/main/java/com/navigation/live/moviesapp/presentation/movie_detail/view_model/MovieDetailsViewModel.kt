package com.navigation.live.moviesapp.presentation.movie_detail.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navigation.live.moviesapp.domain.repository.MoviesRepo
import com.navigation.live.moviesapp.presentation.movie_detail.intent.MovieDetailIntent
import com.navigation.live.moviesapp.presentation.movie_detail.state.MovieDetailResult
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
        fetchMovieJob?.cancel()
        fetchMovieJob = viewModelScope.launch {
            _uiState.update {
                reducer(it, MovieDetailResult.Loading)
            }
            moviesRepo.getMovieById(id).onSuccess { movie ->
                _uiState.update {
                    reducer(it, MovieDetailResult.Success(movie))
                }
            }.onFailure { exception ->
                _uiState.update {
                    reducer(
                        it, MovieDetailResult.Error(
                            exception.message
                                ?: "Unknown error occurred, please try again later"
                        )
                    )
                }
            }
        }
    }

    private fun reducer(
        currentState: MovieDetailUiState, result: MovieDetailResult
    ): MovieDetailUiState {

        return when (result) {
            is MovieDetailResult.Loading -> {
                currentState.copy(
                    isLoading = true, error = null
                )
            }

            is MovieDetailResult.Success -> {
                currentState.copy(
                    isLoading = false, movie = result.movie, error = null
                )
            }

            is MovieDetailResult.Error -> {
                currentState.copy(
                    isLoading = false, error = result.error
                )
            }
        }
    }

}