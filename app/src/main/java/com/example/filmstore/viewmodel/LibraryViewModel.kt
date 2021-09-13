package com.example.filmstore.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmstore.app.App.Companion.getLibraryDao
import com.example.filmstore.model.AppState
import com.example.filmstore.model.repository.LocalRepository
import com.example.filmstore.model.repository.LocalRepositoryImpl

class LibraryViewModel (

    private val historyLiveData: MutableLiveData<AppState> = MutableLiveData(),

    private val filmLocalRepository: LocalRepository = LocalRepositoryImpl(getLibraryDao())
) : ViewModel() {

    fun getLiveData() = historyLiveData

    fun getAllHistory() {
        historyLiveData.value = AppState.Loading
        val handler = Handler(Looper.getMainLooper())
        Thread {
            val history = filmLocalRepository.getAllHistory()
            handler.post(Runnable {
                historyLiveData.value = AppState.SuccessOnHistory(history)
            })
        }.start()
    }

    fun deleteAllHistory() {
        historyLiveData.value = AppState.Loading
        val handler = Handler(Looper.getMainLooper())
        Thread {
            filmLocalRepository.clearHistory()
            val history = filmLocalRepository.getAllHistory()
            handler.post(Runnable {
                historyLiveData.value = AppState.SuccessOnHistory(history)
            })
        }.start()
    }

}