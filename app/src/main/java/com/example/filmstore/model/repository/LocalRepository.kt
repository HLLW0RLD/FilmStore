package com.example.filmstore.model.repository

import com.example.filmstore.model.Film

interface LocalRepository {
    fun getAllHistory(): List<Film>
    fun saveEntity(film : Film)
}