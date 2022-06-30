package com.example.noteapp.navigation

import com.example.noteapp.model.Note

sealed class NavigationSealed(val route:String){

    object NoteScreen:NavigationSealed("home")
    object NoteDetails:NavigationSealed("{note}/details"){
        fun createRoute(note: Note)="$note/details"
    }

}
