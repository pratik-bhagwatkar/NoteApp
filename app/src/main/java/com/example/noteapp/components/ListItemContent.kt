package com.example.noteapp.components

import android.os.Build
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.noteapp.model.Note
import com.example.noteapp.util.formatDate

@Composable
fun CreateListContent(
    note: Note,
    modifier: Modifier = Modifier,
    onNoteClicked:(Note)-> Unit
) {
    Card(
        modifier = modifier.clickable {
            onNoteClicked(note)
        },
        backgroundColor = Color.Blue.copy(alpha = 0.1f),
        shape = RoundedCornerShape(topEnd = 33.dp,bottomStart = 33.dp),
        elevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${note.title}",
                style = MaterialTheme.typography.subtitle2,
            )

            Text(
                text = "${note.des}",
                style = MaterialTheme.typography.subtitle2,
            )

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Text(
                    text = "Date: ${formatDate(note.entryDate.time)}",
                    style = MaterialTheme.typography.subtitle2,
                )
            }
        }
    }
}