package com.example.filmstore.model.DTO

data class FactDTO (val movie_id : Int,
               val title : String?,
               val poster_path : String?,
               val genre : String?,
               val release : String?,
               val description : String?)