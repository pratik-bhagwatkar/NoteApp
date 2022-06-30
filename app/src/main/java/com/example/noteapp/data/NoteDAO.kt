package com.example.noteapp.data

import androidx.room.*
import com.example.noteapp.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(note: Note)

    @Query("Select * from tbl_note")
    fun getAllNotes(): Flow<List<Note>>

    @Query("Select * from tbl_note where id =:id")
    suspend fun getNoteById(id:String):Note

    @Query("Delete from tbl_note where id =:id")
    suspend fun deleteNote(id:String)

    @Query("Delete from tbl_note")
    suspend fun deleteAllNote()

    @Query("UPDATE tbl_note SET title = :title, description = :des WHERE id =:id")
    suspend fun updateNote(title:String,des:String,id: String)
}