package com.toren.noteapp.db

import androidx.room.*
import com.toren.noteapp.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM notes ORDER BY noteText ASC")
    fun getNotes() : Flow<List<Note>>

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM notes WHERE id =:id")
    fun getNote(id : Int) : Flow<Note>

    @Update
    suspend fun updateNote(note: Note)

}