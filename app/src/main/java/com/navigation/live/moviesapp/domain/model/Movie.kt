package com.navigation.live.moviesapp.domain.model

data class Movie(
    val id: String,
    val title: String,
    val originalTitle: String,
    val originalTitleRomanised: String,
    val image: String,
    val movieBanner: String,
    val description: String,
    val director: String,
    val producer: String,
    val releaseDate: String,
    val runningTime: Int, // Converted to Int for domain
    val rtScore: Int, // Converted to Int for domain
    val people: List<String>,
    val species: List<String>,
    val locations: List<String>,
    val vehicles: List<String>,
    val url: String
)
