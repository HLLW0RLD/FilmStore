package com.example.filmstore.viewmodel

import android.telecom.Call
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmstore.model.AppState
import com.example.filmstore.model.DTO.FactDTO
import com.example.filmstore.model.DTO.FilmDTO
import com.example.filmstore.model.Film
import com.example.filmstore.model.repository.DetailsRepository
import com.example.filmstore.model.repository.DetailsRepositoryImpl
import okhttp3.Response
import java.io.IOException
import javax.security.auth.callback.Callback

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"

class DetailsViewModel(
    val detailsLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val detailsRepository: DetailsRepository = DetailsRepositoryImpl(RemoteDataSource()),
    private val historyRepository: LocalRepository = LocalRepositoryImpl(getHistoryDao())
) : ViewModel() {

    fun getFilmFromRemoteSource(name : String) {
        detailsRepository.getFilmDetailsFromServer(name, callBack)
    }

    fun saveFilmToDB(film : Film) {
        historyRepository.saveEntity(film)
    }

    private val callBack = object : Callback<FilmDTO> {

        @Throws(IOException::class)
        override fun onResponse(call: Call<FilmDTO>, response: Response<FilmDTO>) {
            val serverResponse: FilmDTO? = response.body()
            detailsLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<FilmDTO>, t: Throwable) {
            detailsLiveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }
    }

    fun checkResponse(serverResponse: FilmDTO): AppState {
        val fact: FactDTO? = serverResponse.fact
        return if (fact?.temp == null || fact.feels_like == null || fact.condition.isNullOrEmpty()) {
            AppState.Error(Throwable(CORRUPTED_DATA))
        } else {
            AppState.Success(convertDtoToModel(serverResponse))
        }
    }
}