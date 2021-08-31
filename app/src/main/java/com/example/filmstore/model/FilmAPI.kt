package com.example.filmstore.model

import com.example.filmstore.model.DTO.FilmDTO
import com.example.filmstore.model.DTO.FilmDetailDTO
import com.example.filmstore.model.DTO.GenresDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

private const val FIELD_API_KEY = "api_key"
private const val FIELD_LANG = "language"
private const val FIELD_GENRES = "with_genres"
private const val ENDPOINT_LIST_FILMS = "discover/movie"
private const val ENDPOINT_ONE_FILM = "movie/{id}"
private const val ENDPOINT_GENRES = "genre/movie/list"

interface FilmAPI {

    @GET(ENDPOINT_LIST_FILMS)
    fun getFilms(
        @Query(FIELD_API_KEY) apikey: String,
        @Query(FIELD_LANG) lang: String,
        @Query(FIELD_GENRES) genres: String?
    ) : Call<FilmDTO>

    @GET(ENDPOINT_ONE_FILM)
    fun getFilm(
        @Path("id") id: Int,
        @Query(FIELD_API_KEY) apikey: String,
        @Query(FIELD_LANG) lang: String
    ) : Call<FilmDetailDTO>

    @GET(ENDPOINT_GENRES)
    fun getGenres(
        @Query(FIELD_API_KEY) apikey: String,
        @Query(FIELD_LANG) lang: String
    ) : Call<GenresDTO>
}