package com.navigation.live.moviesapp.data.repository

import com.navigation.live.moviesapp.data.remote.MoviesApiService
import com.navigation.live.moviesapp.data.remote.dto.MovieDto
import com.navigation.live.moviesapp.data.remote.mapper.Mapper
import com.navigation.live.moviesapp.domain.model.Movie
import com.navigation.live.moviesapp.domain.repository.MoviesRepo
import javax.inject.Inject

class MoviesRepoImplementation @Inject constructor(
    private val apiService: MoviesApiService,
    private val mapper: Mapper<MovieDto, Movie>
) : MoviesRepo {
    override suspend fun getAllMovies(): Result<List<Movie>> {
        return try {
            val dtoList = apiService.getAllMovies()
            val moviesList = dtoList.map { mapper.toDomain(it) }
            Result.success(moviesList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}