package com.navigation.live.moviesapp.data.remote

import com.navigation.live.moviesapp.data.remote.dto.MovieDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApiService {
    @GET("films")
    suspend fun getAllMovies(): List<MovieDto>

    @GET("films/{id}")
    suspend fun getMovieById(@Path("id") id: String): MovieDto

}

