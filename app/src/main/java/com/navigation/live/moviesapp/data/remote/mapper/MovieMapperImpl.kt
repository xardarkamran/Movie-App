package com.navigation.live.moviesapp.data.remote.mapper

import com.navigation.live.moviesapp.data.remote.dto.MovieDto
import com.navigation.live.moviesapp.domain.model.Movie

class MovieMapperImpl : Mapper<MovieDto, Movie> {
    override fun toDomain(dto: MovieDto): Movie {
        return Movie(
            id = dto.id,
            title = dto.title,
            originalTitle = dto.originalTitle,
            originalTitleRomanised = dto.originalTitleRomanised,
            image = dto.image,
            movieBanner = dto.movieBanner,
            description = dto.description,
            director = dto.director,
            producer = dto.producer,
            releaseDate = dto.releaseDate,
            runningTime = dto.runningTime.toIntOrNull() ?: 0,
            rtScore = dto.rtScore.toIntOrNull() ?: 0,
            people = dto.people,
            species = dto.species,
            locations = dto.locations,
            vehicles = dto.vehicles,
            url = dto.url
        )
    }

    override fun toDto(domain: Movie): MovieDto {
        return MovieDto(
            id = domain.id,
            title = domain.title,
            originalTitle = domain.originalTitle,
            originalTitleRomanised = domain.originalTitleRomanised,
            image = domain.image,
            movieBanner = domain.movieBanner,
            description = domain.description,
            director = domain.director,
            producer = domain.producer,
            releaseDate = domain.releaseDate,
            runningTime = domain.runningTime.toString(),
            rtScore = domain.rtScore.toString(),
            people = domain.people,
            species = domain.species,
            locations = domain.locations,
            vehicles = domain.vehicles,
            url = domain.url
        )
    }
}