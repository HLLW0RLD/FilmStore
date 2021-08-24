package com.example.filmstore.room

import androidx.room.*

const val ID = "id"
const val NAME = "name"


@Entity
data class LibraryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val posterPath: String,
    val overview: String,
    val year: Int,
    val country: String
)