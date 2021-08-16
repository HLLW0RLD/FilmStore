package com.example.filmstore.view

import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.filmstore.model.DTO.FilmDTO
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

private const val FILM_API_KEY = "3983f45e5c2a43172ecbd336102b8339"

class FilmLoader(
    private val listener: FilmLoaderListener,
    private val id: Int
) {

    interface FilmLoaderListener {
        fun onLoaded(filmDTO: FilmDTO)
        fun onFailed(throwable: Throwable)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun loadFilm() {
        try {
           val uri =
               URL("https://api.themoviedb.org/3/movie/popular?api_key=3983f45e5c2a43172ecbd336102b8339")
            val handler = Handler(Looper.myLooper()!!)
            Thread(Runnable {
                lateinit var urlConnection: HttpsURLConnection
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.addRequestProperty(
                        "db_film_api_key",
                        FILM_API_KEY
                    )
                    urlConnection.readTimeout = 10000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))

                    val filmDTO: FilmDTO =
                        Gson().fromJson(getLines(bufferedReader), FilmDTO::class.java)
                    handler.post { listener.onLoaded(filmDTO) }
                } catch (e: Exception) {
                    Log.e("FILM", "Fail connection", e)
                    e.printStackTrace()
                    listener.onFailed(e)

                } finally {
                    urlConnection.disconnect()
                }
            }).start()
        } catch (e: MalformedURLException) {
            Log.e("FILM", "Fail URI", e)
            e.printStackTrace()
            listener.onFailed(e)
            //Обработка ошибки
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

}