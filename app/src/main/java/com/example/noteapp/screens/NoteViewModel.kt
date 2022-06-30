package com.example.noteapp.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.NoteDataSource
import com.example.noteapp.model.Note
import com.example.noteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {


   // var noteList = mutableStateListOf<Note>()
    private val _noteList= MutableStateFlow<List<Note>>(emptyList())
    val noteList =_noteList.asStateFlow()

    val title = mutableStateOf("")
    val description = mutableStateOf("")
    val isError = mutableStateOf(false)

    init {

        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes()
                .distinctUntilChanged()
                .collect{ listNotes ->
                    if (listNotes.isNullOrEmpty()){

                    }else{
                        _noteList.value=listNotes
                    }
                }
        }
    }

    fun updateFields(note: Note?){
        if (note!=null){
            title.value=note.title
            description.value=note.des
        }else{
            title.value=""
            description.value=""
        }

    }


    fun addNote(note: Note){
        viewModelScope.launch {
            repository.addNotes(note = note)
        }
    }

    fun updateNote(title:String,des:String,id: String){
        viewModelScope.launch {
            repository.updateNotes(
                title = title,
                des = des,
                id=id)
        }
    }

    fun deleteNoteByID(id:String){
        viewModelScope.launch {
            repository.deleteNote(id)
        }
    }

    fun removeAllNotes(){
        viewModelScope.launch {
            repository.deleteAllNotes()
        }
    }

}