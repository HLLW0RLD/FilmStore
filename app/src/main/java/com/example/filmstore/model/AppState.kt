package com.example.filmstore.model

sealed class AppState {
    data class Success(val filmData: List<Film>) : AppState()
    class Error(val error: Throwable) : AppState()
}