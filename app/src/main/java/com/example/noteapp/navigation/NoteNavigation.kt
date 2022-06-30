package com.example.noteapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.noteapp.model.Note
import com.example.noteapp.screens.NoteDetails
import com.example.noteapp.screens.NoteScreen
import com.example.noteapp.screens.NoteViewModel

@ExperimentalComposeUiApi
@Composable
fun NoteNavigation(){

    val navController= rememberNavController()
    val noteViewModel:NoteViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = NavigationSealed.NoteScreen.route
    ){
        composable(
            route= NavigationSealed.NoteScreen.route
        ){

            val noteList=noteViewModel.noteList.collectAsState().value
            NoteScreen(
                navController=navController,
                list = noteList,
                onAddNote = {
                    noteViewModel.addNote(it)
                },
                noteViewModel = noteViewModel
            )

        }

        composable(
            route = NavigationSealed.NoteDetails.route
        ){
            val note=navController.previousBackStackEntry?.savedStateHandle?.get<Note>("note")
            note?.let {
                NoteDetails(
                    navController = navController,
                    note = it,
                    noteViewModel = noteViewModel,
                    onUpdate = { title: String, des: String, id: String ->
                        noteViewModel.updateNote(title,des,id)
                    })
            }

        }
    }

}