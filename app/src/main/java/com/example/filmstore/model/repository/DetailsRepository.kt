package com.example.filmstore.model.repository

import com.example.filmstore.model.DTO.FilmDTO
import retrofit2.Callback


interface DetailsRepository {
    fun getFilmDetailsFromServer(
        id: Int,
        callback: Callback<FilmDTO>
    )
}