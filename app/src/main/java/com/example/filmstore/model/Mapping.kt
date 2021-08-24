package com.example.filmstore.model

import com.example.filmstore.model.DTO.FilmDTO
import com.example.filmstore.room.LibraryEntity


fun convertDtoToModel(filmDTO: FilmDTO): List<Film> {
    val fact = filmDTO.fact!!
    return listOf(
        Film(fact.id,
            fact.name,
            fact.posterPath,
            fact.overview,
            fact.year,
            fact.country
        )
    )
}

fun convertLibraryEntityToFilm(entityList: List<LibraryEntity>): List<Film> {
    return entityList.map {
        Film(it.id, it.name, it.posterPath, it.overview, it.year, it.country)
    }
}

fun convertFilmToEntity(film : Film): LibraryEntity {
    return LibraryEntity(film.id, film.name, film.posterPath, film.overview, film.year, film.country)
}