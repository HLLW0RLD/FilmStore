package com.example.filmstore.model.repository

import com.example.filmstore.model.DTO.FilmDTO
import retrofit2.Callback


class DetailsRepositoryImpl(private val remoteDataSource: RemoteDataSource) : DetailsRepository {
    override fun getFilmDetailsFromServer(
        name: Int,
        callback: Callback<FilmDTO>
    ) {
        remoteDataSource.getFilmDetails(name, callback)
    }
}