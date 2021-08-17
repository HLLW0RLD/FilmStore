package com.example.filmstore.model.repository

import com.example.filmstore.model.Film

interface Repository {

    fun getAllFilms(): List<Film>

}