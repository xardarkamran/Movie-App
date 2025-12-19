package com.navigation.live.moviesapp.domain.repository

import com.navigation.live.moviesapp.domain.model.Movie

interface MoviesRepo {
    suspend fun getAllMovies():Result<List<Movie>>
}