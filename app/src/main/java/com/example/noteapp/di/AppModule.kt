package com.example.noteapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapp.data.NoteDAO
import com.example.noteapp.data.NoteDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Singleton
    @Provides
    fun provideNoteDB(@ApplicationContext context: Context):NoteDB =
        Room.databaseBuilder(
            context,
            NoteDB::class.java,
            "notes_db")
            .fallbackToDestructiveMigration()
            .build()



    @Singleton
    @Provides
    fun provideNotesDao(noteDB: NoteDB):NoteDAO{
        return noteDB.noteDao()
    }

}