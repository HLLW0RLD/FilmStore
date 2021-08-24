package com.example.filmstore.model.repository

import com.example.filmstore.model.DTO.FilmDTO

class DetailsRepositoryImpl(private val remoteDataSource: RemoteDataSource) : DetailsRepository {
    override fun getFilmDetailsFromServer(
        name : String,
        callback: retrofit2.Callback<FilmDTO>
    ) {
        remoteDataSource.getFilmDetails(name, callback)
    }
}