package com.example.filmstore.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmstore.model.AppState
import com.example.filmstore.model.DTO.*
import com.example.filmstore.model.Film
import com.example.filmstore.model.FilmDetail
import com.example.filmstore.model.RemoteDataSource.RemoteDataSource
import com.example.filmstore.model.repository.RemoteRepository
import com.example.filmstore.model.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Thread.sleep
import java.time.LocalDate

private const val SERVER_ERROR = "Ошибка загрузки данных"

class MainViewModel(
    private val filmsLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val filmRemoteRepository: RemoteRepository =
        RemoteRepository(RemoteDataSource())
) : ViewModel() {

    fun getLiveData() = filmsLiveData

    fun getListFilmFromRemote(genres: String?) {
        filmsLiveData.value = AppState.Loading
        filmRemoteRepository.getFilmsList(genres, callbackList)
    }

    fun getFilmDetailFromRemote(film: Film) {
        filmsLiveData.value = AppState.Loading
        filmRemoteRepository.getFilm(film.id,callbackFilm)
    }

    fun getGenresListFromRemote() {
        filmsLiveData.value = AppState.Loading
        filmRemoteRepository.getGenres(callbackListGenres)
    }

    private val callbackList = object : Callback<FilmDTO> {

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onResponse(call: Call<FilmDTO>, response: Response<FilmDTO>) {
            val serverResponse: FilmDTO? = response.body()
            filmsLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    val requestURL = response.toString().substringAfter("url=").replace("}","")
                    chekResponseList(serverResponse, requestURL)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<FilmDTO>, t: Throwable) {
            filmsLiveData.postValue(AppState.Error(Throwable(SERVER_ERROR)))
        }

    }

    private val callbackFilm = object : Callback<FilmDetailDTO> {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun onResponse(call: Call<FilmDetailDTO>, response: Response<FilmDetailDTO>) {
            val serverResponse: FilmDetailDTO? = response.body()
            filmsLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    chekResponseFilm(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<FilmDetailDTO>, t: Throwable) {
            filmsLiveData.postValue(AppState.Error(Throwable(SERVER_ERROR)))
        }

    }

    private val callbackListGenres = object : Callback<GenresDTO> {
        override fun onResponse(call: Call<GenresDTO>, response: Response<GenresDTO>) {
            val serverResponse: GenresDTO? = response.body()
            filmsLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    chekResponseGenres(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<GenresDTO>, t: Throwable) {
            filmsLiveData.postValue(AppState.Error(Throwable(SERVER_ERROR)))
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun chekResponseList(serverResponse: FilmDTO, requestURL: String): AppState =
        if (serverResponse.results != null) {
            if (requestURL.contains("with_genres")){
                val genreID = requestURL.substringAfter("with_genres=").substringBefore("&").toInt()
                AppState.SuccessOnListByGenre(convertFilmsFromDTO(serverResponse), genreID)
            } else {
                AppState.SuccessOnList(convertFilmsFromDTO(serverResponse))
            }
        } else {
            AppState.Error(Throwable(SERVER_ERROR))
        }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun chekResponseFilm(serverResponse: FilmDetailDTO): AppState =
        if (serverResponse.id != null) {
            AppState.SuccessOnFilm(convertFilmDetailFromFilmDetailDTO(serverResponse))
        } else {
            AppState.Error(Throwable(SERVER_ERROR))
        }

    private fun chekResponseGenres(serverResponse: GenresDTO): AppState =
        if (serverResponse.genres != null) {
            AppState.SuccessOnGenres(serverResponse.genres)
        } else {
            AppState.Error(Throwable(SERVER_ERROR))
        }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun convertFilmsFromDTO(filmsDTO: FilmDTO): List<Film> {
        val listFilms = mutableListOf<Film>()
        for (film in filmsDTO.results!!) {
            var year: Int
            try {
                year = LocalDate.parse(film.release_date).year
            } catch (e: Exception) {
                Log.e(e.toString(), film.title + film.id.toString())
                year = 0
            }
            with(film) {
                listFilms.add(
                    Film(
                        id!!,
                        title!!,
                        "https://image.tmdb.org/t/p/original/${poster_path!!}",
                        vote_average!!,
                        year
                    )
                )
            }
        }
        return listFilms
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun convertFilmDetailFromFilmDetailDTO(filmDetailDTO: FilmDetailDTO): FilmDetail {
        val date = try {
            LocalDate.parse(filmDetailDTO.release_date)
        } catch (e: Exception) {
            Log.e(e.toString(), filmDetailDTO.title + filmDetailDTO.id.toString())
            LocalDate.of(0, 1, 1)
        }
        return FilmDetail(
            filmDetailDTO.id!!,
            filmDetailDTO.title!!,
            "https://image.tmdb.org/t/p/original/${filmDetailDTO.poster_path!!}",
            filmDetailDTO.vote_average!!,
            date,
            filmDetailDTO.budget!!,
            genresToString(filmDetailDTO.genres!!),
            filmDetailDTO.overview!!,
            countriesToString(filmDetailDTO.production_countries!!)
        )
    }

    private fun genresToString(genres: List<GenreDTO>): String {
        val genresString = mutableListOf<String>()
        for (genre in genres) {
            genre.name?.let { genresString.add(it) }
        }
        return genresString
            .toString()
            .replace("[", "", true)
            .replace("]", "", true)
    }

    private fun countriesToString(countries: List<CountryDTO>): String {
        val countriesString = mutableListOf<String>()
        for (country in countries) {
            country.name?.let { countriesString.add(it) }
        }
        return countriesString
            .toString()
            .replace("[", "", true)
            .replace("]", "", true)
    }

}