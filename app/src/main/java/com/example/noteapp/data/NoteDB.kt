package com.example.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.noteapp.model.Note
import com.example.noteapp.util.DateConverter
import com.example.noteapp.util.UUIDConverter

@Database(entities = [Note::class],version = 1,exportSchema = false)
@TypeConverters(DateConverter::class,UUIDConverter::class)
abstract class NoteDB:RoomDatabase() {

    abstract fun noteDao():NoteDAO

}