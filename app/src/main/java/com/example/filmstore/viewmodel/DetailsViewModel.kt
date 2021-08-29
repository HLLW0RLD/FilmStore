package com.example.filmstore.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmstore.app.App.Companion.getLibraryDao
import com.example.filmstore.model.AppState
import com.example.filmstore.model.DTO.FactDTO
import com.example.filmstore.model.DTO.FilmDTO
import com.example.filmstore.model.Film
import com.example.filmstore.model.convertDtoToModel
import com.example.filmstore.model.repository.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"

class DetailsViewModel(
    val detailsLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val detailsRepository: DetailsRepository = DetailsRepositoryImpl(RemoteDataSource()),
    private val libraryRepository: LocalRepository = LocalRepositoryImpl(getLibraryDao())
) : ViewModel() {

    fun getFilmFromRemoteSource(id: Int) {
        detailsRepository.getFilmDetailsFromServer(id, callBack)
    }

    fun saveFilmToDB(film : Film) {
        libraryRepository.saveEntity(film)
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
        val fact : FactDTO? = serverResponse.fact
          return  AppState.Success(convertDtoToModel(serverResponse))
        }
    }
