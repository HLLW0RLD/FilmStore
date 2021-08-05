package com.example.filmstore.viewmodel

import androidx.lifecycle.ViewModel
import com.example.filmstore.model.repository.Repository
import com.example.filmstore.model.repository.RepositoryImpl

class MainViewModel(private val repository: Repository = RepositoryImpl()) :
    ViewModel() {
}