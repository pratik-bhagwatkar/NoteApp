package com.example.noteapp.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noteapp.components.AppBar
import com.example.noteapp.components.DetailAppBar
import com.example.noteapp.components.NoteButton
import com.example.noteapp.components.TextFields
import com.example.noteapp.model.Note
import com.example.noteapp.navigation.NavigationSealed

@ExperimentalComposeUiApi
@Composable
fun NoteDetails(
    navController: NavController,
    note: Note,
    noteViewModel: NoteViewModel,
    onUpdate:(title:String,des:String,id:String)->Unit
){
    noteViewModel.updateFields(note = note)
    Scaffold (
        topBar = {
            DetailAppBar(
                title = "Notes Details",
                onBackPress = {
                    noteViewModel.updateFields(note = null)
                    navController.popBackStack()
                },
                onDeletePress = {
                    noteViewModel.deleteNoteByID(note.id.toString())
                    noteViewModel.updateFields(note = null)
                    navController.popBackStack()
                }
            )
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
                text = "Update",
                onClick = {
                    if (noteViewModel.title.value.isNotEmpty() && noteViewModel.description.value.isNotEmpty()){
                        onUpdate( noteViewModel.title.value,
                          noteViewModel.description.value,
                            note.id.toString())
                        Toast.makeText(context,"Note Update Successfully..!", Toast.LENGTH_SHORT).show()
                        Log.i("za","Note Update Successfully..!")
                        noteViewModel.updateFields(note = null)
                        navController.popBackStack()
                    }else{
                        noteViewModel.isError.value = true
                        Toast.makeText(context,"Please Add valid Data..!", Toast.LENGTH_SHORT).show()
                        Log.i("za","Please Add valid Data..!")
                    }
                },
                modifier = Modifier.fillMaxWidth(0.5f)
            )





        }
    }
    
}