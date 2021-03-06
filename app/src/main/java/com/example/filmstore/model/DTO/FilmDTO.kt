package com.example.filmstore.model.DTO

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmDTO(
    val results: List<FilmToListDTO>?,
    val total_pages: Int?
) : Parcelable