package com.example.filmstore.model.repository

import com.example.filmstore.model.Film

class RepositoryImpl : Repository{

    override fun getAllFilms() = listOf(
        Film(1, "text", "text", "text", "2020", "text"),
        Film(50, "text", "text", "text",  "2020", "text"),
        Film(100, "text", "text", "text",  "2020", "text"),
        Film(767, "text", "text", "text",  "2020", "text"),
        Film(666, "text", "text", "text",  "2020", "text"),
        Film(345, "text", "text", "text",  "2020", "text"),
        Film(20, "text", "text", "text",  "2020", "text"),
        Film(4683, "text", "text", "text",  "2020", "text"),
        Film(864, "text", "text", "text",  "2020", "text"),
        Film(2345, "text", "text", "text", "2020", "text"),
        Film(34, "text", "text", "text",  "2020", "text"),
        Film(260, "text", "text", "text",  "2020", "text"),
        Film(2, "text", "text", "text", "2020", "text"),
        Film(890, "text", "text", "text",  "2020", "text"),
        Film(641, "text", "text", "text",  "2020", "text"),
        Film(7, "text", "text", "text",  "2020", "text"),
    )
}