package com.example.filmstore.model.repository

import com.example.filmstore.model.Film

class RepositoryImpl : Repository{

    override fun getFilm() = Film()

}