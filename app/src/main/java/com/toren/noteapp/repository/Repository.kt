package com.toren.noteapp.repository

import com.toren.noteapp.db.RoomDao
import com.toren.noteapp.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject
constructor(private val dao: RoomDao){

    fun getNotes() = dao.getNotes()

    suspend fun insertNote(note: Note) = dao.insertNote(note)

    suspend fun deleteNote(note: Note) = dao.deleteNote(note)

    suspend fun updateNote(note: Note) = dao.updateNote(note)

    fun getNote(id : Int): Flow<Note> = dao.getNote(id)
}