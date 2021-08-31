package com.example.filmstore.model.repository

import com.example.filmstore.model.DTO.FilmDTO
import com.example.filmstore.model.DTO.FilmDetailDTO
import com.example.filmstore.model.DTO.GenresDTO
import com.example.filmstore.model.RemoteDataSource.RemoteDataSource
import retrofit2.Callback

class RemoteRepository(private val remoteFilmsSource: RemoteDataSource) : Repository {

    override fun getFilmsList(genres: String?, callback: Callback<FilmDTO>) {
        remoteFilmsSource.getFilmsList(genres, callback)
    }

    override fun getFilm(filmID: Int, callback: Callback<FilmDetailDTO>) {
        remoteFilmsSource.getFilmDetail(filmID, callback)
    }

    override fun getGenres(callback: Callback<GenresDTO>) {
        remoteFilmsSource.getGenreList(callback)
    }
}