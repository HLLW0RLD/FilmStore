package com.example.filmstore.view.Adapter

import com.example.filmstore.model.Film

interface SetOnItemViewClickListener {

    fun setOnItemViewClickListener(onItemViewClickListener: (Film) -> Unit)

}