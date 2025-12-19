package com.navigation.live.moviesapp.presentation.movies_list.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navigation.live.moviesapp.domain.repository.MoviesRepo
import com.navigation.live.moviesapp.presentation.movies_list.intent.MovieListIntent
import com.navigation.live.moviesapp.presentation.movies_list.state.MovieListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllMoviesViewModel @Inject constructor(
    private val moviesRepo: MoviesRepo
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieListUiState())
    val uiState = _uiState.asStateFlow()

    private var fetchAllMovieListJob: Job? = null

    fun handleIntent(movieListIntent: MovieListIntent) {
        when (movieListIntent) {
            is MovieListIntent.FetchMoviesList -> fetchAllMovies()
        }
    }

    private fun fetchAllMovies() {
        fetchAllMovieListJob?.cancel()
        fetchAllMovieListJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            moviesRepo.getAllMovies()
                .onSuccess {movieList->
                    delay(2000L)
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            list = movieList
                        )
                    }
                }
                .onFailure {exception->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = exception.message ?: "Unknown error occurred, please try again later"
                        )
                    }
                }

        }
    }

}
