package com.example.filmstore.model.repository

import com.example.filmstore.BuildConfig
import com.example.filmstore.model.DTO.FilmDTO
import com.example.filmstore.model.FilmAPI
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    private val FILM_API_KEY = "3983f45e5c2a43172ecbd336102b8339"

    private val filmAPI = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        ).build().create(FilmAPI::class.java)

    fun getFilmDetails(name : String, callback: Callback<FilmDTO>) {
        filmAPI.getFilm(FILM_API_KEY, name).enqueue(callback)
    }
}