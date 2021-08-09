package com.example.filmstore.view

import android.os.Build
import android.os.Handler
import android.os.Looper
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.viewbinding.BuildConfig
import com.example.filmstore.model.DTO.FilmDTO
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class FilmLoader (
    private val listener: FilmLoaderListener,
    private val name : String
) {

    interface FilmLoaderListener {
        fun onLoaded(filmDTO: FilmDTO)
        fun onFailed(throwable: Throwable)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun loadFilm() {
        try {
            val uri =
                URL("https://api.weather.yandex.ru/v2/informers?name=${name}")
            val handler = Handler(Looper.myLooper()!!)
            Thread(Runnable {
                lateinit var urlConnection: HttpsURLConnection
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.addRequestProperty(
                        "X-Yandex-API-Key",
                        BuildConfig.FILM_API_KEY
                    )
                    urlConnection.readTimeout = 10000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))

                    val weatherDTO: FilmDTO =
                        Gson().fromJson(getLines(bufferedReader), FilmDTO::class.java)
                    handler.post { listener.onLoaded(weatherDTO) }
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