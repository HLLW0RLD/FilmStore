package com.example.filmstore.model.repository

import com.example.filmstore.model.Film

interface LocalRepository {
    fun getAllLibrary(): List<Film>
    fun saveEntity(film : Film)
}