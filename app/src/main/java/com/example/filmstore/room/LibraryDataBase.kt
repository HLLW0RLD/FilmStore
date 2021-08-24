package com.example.filmstore.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LibraryEntity::class], version = 1, exportSchema = false)
abstract class LibraryDataBase : RoomDatabase() {
    abstract fun LibraryDao(): LibraryDao
}