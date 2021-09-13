package com.example.filmstore.model.repository

import com.example.filmstore.model.Film
import com.example.filmstore.model.FilmDetail

interface LocalRepository {
    fun getAllHistory(): List<Film>
    fun clearHistory()
    fun updateEntity(film: Film)
    fun saveEntity(film: Film)
}