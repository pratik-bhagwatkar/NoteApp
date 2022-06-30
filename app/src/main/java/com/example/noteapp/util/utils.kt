package com.example.noteapp.util

import java.text.SimpleDateFormat
import java.util.*




    fun formatDate(timeStamp:Long):String{
        val date= Date(timeStamp)
        val fomat=SimpleDateFormat("EEE, d MMM hh:mm aaa", Locale.getDefault())
        return fomat.format(date)
    }
