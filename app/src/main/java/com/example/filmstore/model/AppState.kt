package com.example.filmstore.model

import com.example.filmstore.model.DTO.GenreDTO

sealed class AppState {
    data class SuccessOnList(val listFilms: List<Film>) : AppState()
    data class SuccessOnFilm(val filmDetail: FilmDetail) : AppState()
    data class SuccessOnGenres(val genres: List<GenreDTO>) : AppState()
    data class SuccessOnListByGenre(val listFilms: List<Film>, val genreID: Int) : AppState()
    data class SuccessOnHistory(val history: List<Film>) : AppState()
    class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}