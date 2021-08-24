package com.example.filmstore.model.repository

import com.example.filmstore.model.DTO.FilmDTO

interface DetailsRepository {
    fun getFilmDetailsFromServer(
        name : String,
        callback: retrofit2.Callback<FilmDTO>
    )
}