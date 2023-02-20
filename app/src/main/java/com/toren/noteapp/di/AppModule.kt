package com.toren.noteapp.di

import android.content.Context
import androidx.room.Room
import com.toren.noteapp.db.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context, RoomDatabase::class.java,
        "db_notes").build()

    @Singleton
    @Provides
    fun provideRoomDao(db: RoomDatabase) = db.roomDao()
}