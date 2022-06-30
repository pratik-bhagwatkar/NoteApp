package com.example.noteapp.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun NoteButton(
    text:String,
    modifier: Modifier =Modifier,
    onClick:()->Unit,
    enabled:Boolean=true
){
    
    Button(
        onClick = { 
            onClick()
        },
        modifier = modifier,
        enabled = enabled,
        shape = CircleShape
    ) {
        Text(text = text)
    }

}