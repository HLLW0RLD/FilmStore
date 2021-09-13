package com.example.filmstore.room

import android.database.Cursor
import androidx.room.*


@Dao
interface LibraryDao {
    @Query("SELECT * FROM LibraryEntity ORDER BY LibraryEntity.time DESC")
    fun all(): List<LibraryEntity>

    @Query("SELECT * FROM LibraryEntity WHERE name LIKE :name")
    fun getDataByWord(name: String): List<LibraryEntity>

    @Query("DELETE FROM LibraryEntity")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: LibraryEntity)

    @Update
    fun update(entity: LibraryEntity)

    @Delete
    fun delete(entity: LibraryEntity)

}