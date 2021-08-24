package com.example.filmstore.model.DTO

data class FactDTO( val id: Int,
                    val name: String,
                    val posterPath: String,
                    val overview: String,
                    val year: Int,
                    val country: String)