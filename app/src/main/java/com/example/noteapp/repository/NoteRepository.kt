package com.example.noteapp.repository

import com.example.noteapp.data.NoteDAO
import com.example.noteapp.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDAO: NoteDAO) {


    suspend fun addNotes(note:Note){
        noteDAO.insertNotes(note = note)
    }

    suspend fun updateNotes(title:String,des:String,id: String){
        noteDAO.updateNote(
            title = title,
            des = des,
            id=id
        )
    }

    suspend fun deleteNote(noteId:String){
        noteDAO.deleteNote(id = noteId)
    }

    suspend fun deleteAllNotes(){
        noteDAO.deleteAllNote()
    }

    fun getAllNotes(): Flow<List<Note>> {
        return noteDAO.getAllNotes()
    }

}