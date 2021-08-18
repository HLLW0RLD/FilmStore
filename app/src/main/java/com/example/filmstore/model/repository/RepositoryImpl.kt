package com.example.filmstore.model.repository

import com.example.filmstore.R
import com.example.filmstore.model.Film

class RepositoryImpl : Repository {

    override fun getAllFilms() = listOf<Film>(
        Film(
            0,
            "Бойцовский клуб",
            "https://cdn1.ozone.ru/s3/multimedia-c/6016453560.jpg",
            R.string.fight_club_overview.toString(),
            1999,
            "Германия, США"
        ),

        Film(
            1,
            "Д`Артаньян и три мушкетера",
            "https://www.timeout.ru/wp-content/uploads/serials/44076.jpg",
            R.string.three_mush_overview.toString(),
            1979,
            "СССР"
        ),

        Film(
            2,
            "Судный день",
            "https://img11.postila.ru/data/ae/53/52/dc/ae5352dc431a3442dd286a182cffa7acaf92df248eefc14b0c2ad770114ee8c0.jpg",
            R.string.doomsday_overview.toString(),
            2008,
            "Великобритания, США, ЮАР, Германия"
        ),

        Film(
            3,
            "Бойцовский клуб",
            "https://cdn1.ozone.ru/s3/multimedia-c/6016453560.jpg",
            R.string.fight_club_overview.toString(),
            1999,
            "Германия, США"
        ),

        Film(
            4,
            "Д`Артаньян и три мушкетера",
            "https://www.timeout.ru/wp-content/uploads/serials/44076.jpg",
            R.string.three_mush_overview.toString(),
            1979,
            "СССР"
        ),

        Film(
            5,
            "Судный день",
            "https://img11.postila.ru/data/ae/53/52/dc/ae5352dc431a3442dd286a182cffa7acaf92df248eefc14b0c2ad770114ee8c0.jpg",
            R.string.doomsday_overview.toString(),
            2008,
            "Великобритания, США, ЮАР, Германия"
        )
    )
}
