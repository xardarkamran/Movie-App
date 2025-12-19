package com.navigation.live.moviesapp.di

import com.navigation.live.moviesapp.data.remote.MoviesApiService
import com.navigation.live.moviesapp.data.remote.dto.MovieDto
import com.navigation.live.moviesapp.data.remote.mapper.Mapper
import com.navigation.live.moviesapp.data.remote.mapper.MovieMapperImpl
import com.navigation.live.moviesapp.data.repository.MoviesRepoImplementation
import com.navigation.live.moviesapp.domain.model.Movie
import com.navigation.live.moviesapp.domain.repository.MoviesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        moviesApiService: MoviesApiService,
        mapper: Mapper<MovieDto,Movie>
    ): MoviesRepo {
        return MoviesRepoImplementation(
            apiService = moviesApiService,
            mapper = mapper
        )
    }

}