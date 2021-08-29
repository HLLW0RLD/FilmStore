package com.example.filmstore.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmstore.app.App.Companion.getLibraryDao
import com.example.filmstore.model.AppState
import com.example.filmstore.model.repository.LocalRepository
import com.example.filmstore.model.repository.LocalRepositoryImpl

class LibraryViewModel(
    val libraryLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val libraryRepository: LocalRepository = LocalRepositoryImpl(getLibraryDao())
) : ViewModel() {

    fun getAllHistory(){
        libraryLiveData.value = AppState.Loading
        libraryLiveData.value = AppState.Success(libraryRepository.getAllLibrary())
    }
}