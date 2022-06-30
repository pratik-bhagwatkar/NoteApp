package com.example.noteapp.model


import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.time.Instant
import java.time.LocalDateTime
import java.util.*
@Parcelize
@Entity(tableName = "tbl_note")
data class Note(
          @PrimaryKey
          val id: UUID=UUID.randomUUID(),
          @ColumnInfo(name = "title")
          val title:String,
          @ColumnInfo(name = "description")
          val des:String,
          @ColumnInfo(name = "entryDate ")
          val entryDate: Date= Date.from(Instant.now())
        ):Parcelable