package com.example.filmstore.model.repository

import com.example.filmstore.model.Film

class RepositoryImpl : Repository{

    override fun getFilm() = Film()

    override fun getMyFilms() = listOf(
        Film("Film", 7.0, 2020,"text"),
        Film("Top", 10.0, 2021, "best")
    )

    override fun getAllFilms() = listOf(
        Film("Film", 7.0, 2020,"text"),
        Film("Film", 7.0, 2020,"text"),
        Film("Film", 7.0, 2020,"text"),
        Film("Film", 7.0, 2020,"text"),
        Film("Film", 7.0, 2020,"text"),
        Film("Film", 7.0, 2020,"text"),
        Film("Film", 7.0, 2020,"text"),
        Film("Film", 7.0, 2020,"text"),
        Film("Film", 7.0, 2020,"text"),
        Film("Film", 7.0, 2020,"text"),
        Film("Film", 7.0, 2020,"text"),
        Film("Film", 7.0, 2020,"text"),
        Film("Film", 7.0, 2020,"text"),
        Film("Film", 7.0, 2020,"text"),
        Film("Film", 7.0, 2020,"text"),
        Film("Film", 7.0, 2020,"text"),
    )

    override fun getBestFilms() = listOf(
        Film("Top", 10.0, 2021, "best"),
        Film("Top", 10.0, 2021, "best"),
        Film("Top", 10.0, 2021, "best"),
        Film("Top", 10.0, 2021, "best"),
        Film("Top", 10.0, 2021, "best"),
        Film("Top", 10.0, 2021, "best"),
        Film("Top", 10.0, 2021, "best"),
        Film("Top", 10.0, 2021, "best"),
        Film("Top", 10.0, 2021, "best"),
        Film("Top", 10.0, 2021, "best"),
        Film("Top", 10.0, 2021, "best"),
        Film("Top", 10.0, 2021, "best"),
        Film("Top", 10.0, 2021, "best"),
        Film("Top", 10.0, 2021, "best"),
        Film("Top", 10.0, 2021, "best"),
    )
}