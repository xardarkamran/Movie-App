package com.navigation.live.moviesapp.di

import androidx.compose.animation.core.rememberTransition
import com.navigation.live.moviesapp.data.remote.dto.MovieDto
import com.navigation.live.moviesapp.data.remote.mapper.Mapper
import com.navigation.live.moviesapp.data.remote.mapper.MovieMapperImpl
import com.navigation.live.moviesapp.domain.model.Movie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MappersModule {

    @Provides
    @Singleton
    fun providesMapper():Mapper<MovieDto,Movie>{
        return MovieMapperImpl()
    }

}