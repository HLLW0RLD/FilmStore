package com.example.filmstore.model.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.filmstore.model.Film
import com.example.filmstore.room.LibraryDao
import com.example.filmstore.room.LibraryEntity
import java.time.temporal.ChronoField

class LocalRepositoryImpl(private val localDataSource: LibraryDao) : LocalRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getAllHistory(): List<Film> {
        return convertFilmEntityToFilm(localDataSource.all())
    }

    override fun clearHistory() {
        localDataSource.deleteAll()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun updateEntity(film: Film) {
        localDataSource.update(convertFilmToFilmEntity(film))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun saveEntity(film: Film) {
        localDataSource.insert(convertFilmToFilmEntity(film))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun convertFilmToFilmEntity(film: Film): LibraryEntity {
        with(film) {
            return LibraryEntity(
                id.toLong(),
                name,
                posterPath,
                voteAverage,
                releaseDate
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun convertFilmEntityToFilm(list: List<LibraryEntity>): List<Film> {
        var listFilms: MutableList<Film> = mutableListOf()
        for (entity in list) {
            val film = Film(
                entity.id.toInt(),
                entity.name,
                entity.posterPath,
                entity.voteAverage,
            entity.releaseDate)

            listFilms.add(film)
        }
        return listFilms
    }
}