package com.example.filmstore.model

import com.example.filmstore.model.DTO.FilmDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

private const val FILM_API_KEY = "3983f45e5c2a43172ecbd336102b8339"

interface FilmAPI {
    @GET("v2/informers")
    fun getFilm(
        @Header(FILM_API_KEY) token: String,
        @Query("name") id: Int,
    ): Call<FilmDTO>
}