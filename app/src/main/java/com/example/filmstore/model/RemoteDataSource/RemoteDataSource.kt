package com.example.filmstore.model.RemoteDataSource

import com.example.filmstore.model.DTO.FilmDTO
import com.example.filmstore.model.DTO.FilmDetailDTO
import com.example.filmstore.model.DTO.GenresDTO
import com.example.filmstore.model.FilmAPI
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val LANG_RUS = "ru-RU"
private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val TMDB_API_KEY = "3983f45e5c2a43172ecbd336102b8339"

class RemoteDataSource {

    private val listOfFilmsAPI = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(FilmAPI::class.java)

    fun getFilmsList(genres: String?, callback: Callback<FilmDTO>) {
        listOfFilmsAPI.getFilms(TMDB_API_KEY, LANG_RUS, genres).enqueue(callback)
    }

    fun getFilmDetail(filmID: Int, callback: Callback<FilmDetailDTO>) {
        listOfFilmsAPI.getFilm(filmID, TMDB_API_KEY, LANG_RUS).enqueue(callback)
    }

    fun getGenreList(callback: Callback<GenresDTO>) {
        listOfFilmsAPI.getGenres(TMDB_API_KEY, LANG_RUS).enqueue(callback)
    }
}