package com.example.filmstore.model.repository

import com.example.filmstore.model.Film

class LocalRepositoryImpl(private val localDataSource: HistoryDao) : LocalRepository {
    override fun getAllHistory(): List<Film> {
        return convertHistoryEntityToFilm(localDataSource.all())
    }

    override fun saveEntity(film : Film) {
        return localDataSource.insert(convertFilmToEntity(film))
    }
}