package com.navigation.live.moviesapp.presentation.shared.utilz

import com.navigation.live.moviesapp.domain.model.Movie

object MovieMockData {
    val mockMovie = Movie(
        id = "2baf70d1-42bb-4437-b551-e5fed5a87abe",
        title = "Castle in the Sky",
        originalTitle = "天空の城ラピュタ",
        originalTitleRomanised = "Tenkū no shiro Rapyuta",
        image = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/npOnzAbLh6VOIu3naU5QaEcTepo.jpg",
        movieBanner = "https://image.tmdb.org/t/p/w533_and_h300_bestv2/3cyjYtLWCBE1uvWINHFsFnE8LUK.jpg",
        description = "The orphan Sheeta inherited a mysterious crystal that links her to the mythical sky-kingdom of Laputa. With the help of resourceful Pazu and a rollicking band of sky pirates, she makes her way to the ruins of the once-great civilization.",
        director = "Hayao Miyazaki",
        producer = "Isao Takahata",
        releaseDate = "1986",
        runningTime = 124,
        rtScore = 95,
        people = listOf(
            "https://ghibliapi.vercel.app/people/598f7048-74ff-41e0-92ef-87dc1ad980a9"
        ),
        species = listOf(
            "https://ghibliapi.vercel.app/species/af3910a6-429f-4c74-9ad5-dfe1c4aa04f2"
        ),
        locations = listOf(
            "https://ghibliapi.vercel.app/locations/"
        ),
        vehicles = listOf(
            "https://ghibliapi.vercel.app/vehicles/4e09b023-f650-4747-9ab9-eacf14540cfb"
        ),
        url = "https://ghibliapi.vercel.app/films/2baf70d1-42bb-4437-b551-e5fed5a87abe"
    )
}