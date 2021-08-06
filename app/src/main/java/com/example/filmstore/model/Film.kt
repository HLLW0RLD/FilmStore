package com.example.filmstore.model


import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val name: String = "Film",
                val rating: Double = 7.0,
                val year: Int = 2020,
                val description: String = "text") : Parcelable