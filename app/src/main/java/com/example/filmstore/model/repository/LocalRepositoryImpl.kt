package com.example.filmstore.model.repository

import com.example.filmstore.model.Film
import com.example.filmstore.model.convertFilmToEntity
import com.example.filmstore.model.convertLibraryEntityToFilm
import com.example.filmstore.room.LibraryDao

class LocalRepositoryImpl(private val localDataSource: LibraryDao) : LocalRepository {

    override fun getAllLibrary(): List<Film> {
        return convertLibraryEntityToFilm(localDataSource.all())
    }

    override fun saveEntity(film : Film) {
        return localDataSource.insert(convertFilmToEntity(film))
    }
}