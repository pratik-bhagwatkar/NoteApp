package com.example.noteapp.components

import android.provider.ContactsContract
import android.telecom.Call
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapp.model.Note

@Preview
@Composable
fun AppBar(title:String="Note App"){

             TopAppBar(
                 title = {
                     Text(text = title)
                 },
                 elevation = 6.dp
             )

}


@Preview
@Composable
fun DetailAppBar(title: String="Note App",onBackPress:()->Unit={},onDeletePress:()->Unit={}){
    TopAppBar(
        title = {
            Text(text = title)
        },
        elevation = 6.dp,
        navigationIcon = {
            IconButton(onClick = { onBackPress() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back Arrow")
            }
        },
        actions = {
            IconButton(onClick = { onDeletePress() }) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription ="Delete Icon" )
            }

        }
    )

}


