package com.example.filmstore.model.repository

import com.example.filmstore.model.Film

interface Repository {

    fun getFilm() : Film

    fun getBestFilms(): List<Film>

    fun getAllFilms(): List<Film>

}