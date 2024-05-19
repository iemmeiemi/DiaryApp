package com.example.diaryapp.utils

import com.google.firebase.Timestamp
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getDate(timestamp: Timestamp?): Date? {
    return try {
        val date: Date? = timestamp?.toDate()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        dateFormat.parse(date?.toString() ?: "")
    } catch (e: ParseException) {
        null
    }
}