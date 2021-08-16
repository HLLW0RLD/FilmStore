package com.example.filmstore.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(val movie_id : Int,
                val title : String?,
                val poster_path : String?,
                val genre : String?,
                val release : String?,
                val description : String?) : Parcelable