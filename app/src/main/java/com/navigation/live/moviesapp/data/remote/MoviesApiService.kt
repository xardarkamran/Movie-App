package com.navigation.live.moviesapp.data.remote

import com.navigation.live.moviesapp.data.remote.dto.MovieDto
import retrofit2.http.GET

interface MoviesApiService {
    @GET("films")
    suspend fun getAllMovies(): List<MovieDto>
}

