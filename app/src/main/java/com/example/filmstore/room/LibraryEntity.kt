package com.example.filmstore.room

import androidx.room.*

const val ID = "id"
const val NAME = "name"


@Entity
data class LibraryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val posterPath: String,
    val voteAverage: Double,
    val releaseDate: Long,
)