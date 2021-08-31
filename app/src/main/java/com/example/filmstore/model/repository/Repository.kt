package com.example.filmstore.model.repository

import com.example.filmstore.model.DTO.FilmDTO
import com.example.filmstore.model.DTO.FilmDetailDTO
import com.example.filmstore.model.DTO.GenresDTO
import retrofit2.Callback

interface Repository {
    fun getFilmsList(genres: String?, callback: Callback<FilmDTO>)
    fun getFilm(filmID: Int, callback: Callback<FilmDetailDTO>)
    fun getGenres(callback: Callback<GenresDTO>)
}