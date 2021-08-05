package com.example.filmstore.model

class Storage {

    fun getFilms() = listOf<Film>(

        Film("Film", 7.0, "text")

    )

    fun getBestFilms() = listOf<Film>(

        Film("Top", 10.0, "best")

    )

}