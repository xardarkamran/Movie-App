package com.navigation.live.moviesapp.data.remote.mapper

interface Mapper<DTO, Domain> {
    fun toDomain(dto: DTO): Domain
    fun toDto(domain: Domain): DTO
}