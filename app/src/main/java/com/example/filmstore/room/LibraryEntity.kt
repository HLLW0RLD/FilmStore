package com.example.filmstore.room

import androidx.room.*

const val ID = "id"
const val NAME = "name"
const val FILM = "film"

@Entity
data class LibraryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val film: String = "",
    val name: String = ""
)