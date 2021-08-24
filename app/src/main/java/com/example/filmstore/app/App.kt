package com.example.filmstore.app

import android.app.Application
import androidx.room.Room
import com.example.filmstore.room.LibraryDao
import com.example.filmstore.room.LibraryDataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: App? = null
        private var db: LibraryDataBase? = null
        private const val DB_NAME = "Library.db"

        fun getLibraryDao(): LibraryDao {
            if (db == null) {
                synchronized(LibraryDataBase::class.java) {
                    if (db == null) {
                        if (appInstance == null) {
                            throw IllegalStateException("Application ids null meanwhile creating database")
                        }
                        db = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            LibraryDataBase::class.java,
                            DB_NAME)
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return db!!.LibraryDao()
        }
    }
}