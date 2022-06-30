package com.example.noteapp.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noteapp.components.*
import com.example.noteapp.model.Note
import com.example.noteapp.navigation.NavigationSealed

@ExperimentalComposeUiApi
@Composable
fun NoteScreen(
    navController: NavController,
    list:List<Note>,
    onAddNote:(Note)->Unit,
    noteViewModel: NoteViewModel

){

    Scaffold (
        topBar = {
            AppBar("Add Notes")
        }
    ) {

        val context= LocalContext.current

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
               TextFields(
                   text = noteViewModel.title.value,
                   label = "Title",
                   onTextChange = {
                       if (it.all { char->
                         char.isLetter() || char.isWhitespace()
                       })
                           noteViewModel.title.value=it
                   },
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(start = 8.dp, end = 8.dp),
                   isError = noteViewModel.isError.value
               )

            TextFields(
                text = noteViewModel.description.value,
                label = "Add a note",
                onTextChange = {
                    if (it.all { char->
                            char.isLetter() || char.isWhitespace()
                        })
                        noteViewModel.description.value=it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                isError = noteViewModel.isError.value
            )

            Spacer(modifier = Modifier.height(18.dp))

            NoteButton(
                text = "Save",
                onClick = {
                    if (noteViewModel.title.value.isNotEmpty() && noteViewModel.description.value.isNotEmpty()){
                        onAddNote(
                          Note(title = noteViewModel.title.value, des = noteViewModel.description.value)
                        )
                        Toast.makeText(context,"Note Added Successfully..!",Toast.LENGTH_SHORT).show()
                    }else{
                        noteViewModel.isError.value = true
                        Toast.makeText(context,"Please Add valid Data..!",Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(0.5f)
            )

            Spacer(modifier = Modifier.height(18.dp))

            Divider()

            CreateList(navController,list)

        }
    }
}

@Composable
fun CreateList(navController: NavController,list: List<Note>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ){
        items(list){ note->
            CreateListContent(
                note = note,
               modifier = Modifier
                .fillMaxWidth()
                .size(100.dp)){  note ->

                navController.currentBackStackEntry?.savedStateHandle?.set("note",note)
                navController.navigate(
                    route = NavigationSealed.NoteDetails.createRoute(note = note)
                )
            }
        }
    }
}




