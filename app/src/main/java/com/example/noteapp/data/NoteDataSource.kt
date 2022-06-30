package com.example.noteapp.data

import com.example.noteapp.model.Note

class NoteDataSource {

    companion object{
        fun loadNotes():List<Note>{
            return listOf(
                Note(
                    title = "A good day",
                    des = "We went on vaccation"
                )
            )

        }
    }

}