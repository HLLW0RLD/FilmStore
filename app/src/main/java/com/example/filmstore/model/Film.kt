package com.example.filmstore.model


import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val name: String,
    val genre: String,
    val rating: Double = 7.0,
    val year: String,
    val description: String) : Parcelable