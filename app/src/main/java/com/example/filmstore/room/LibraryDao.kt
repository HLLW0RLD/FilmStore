package com.example.filmstore.room

import android.database.Cursor
import androidx.room.*


@Dao
interface LibraryDao {
    @Query("SELECT * FROM LibraryEntity")
    fun all(): List<LibraryEntity>

    @Query("SELECT * FROM LibraryEntity WHERE film LIKE :film")
    fun getDataByWord(film: String): List<LibraryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: LibraryEntity)

    @Update
    fun update(entity: LibraryEntity)

    @Delete
    fun delete(entity: LibraryEntity)

    @Query("DELETE FROM LibraryEntity WHERE id = :id")
    fun deleteById(id: Long)

    @Query("SELECT id, name FROM LibraryEntity")
    fun getLibraryCursor(): Cursor

    @Query("SELECT id, name FROM LibraryEntity WHERE id = :id")
    fun getLibraryCursor(id: Long): Cursor
}