package com.toren.noteapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.toren.noteapp.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class RoomDatabase : RoomDatabase() {

    abstract fun roomDao() : RoomDao
}