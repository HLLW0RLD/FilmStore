package com.example.filmstore.model.DTO

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenresDTO(
    val genres: MutableList<GenreDTO>?
) : Parcelable