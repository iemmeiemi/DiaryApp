package com.example.diaryapp.utils

import android.util.Log
import com.google.firebase.Timestamp
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun getDate(timestamp: Timestamp?): String? {
    return try {
        val date: Date? = timestamp?.toDate()
        //Log.e("datehandler", date.toString())
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        Log.e("datehandler", dateFormat.format(date))
        dateFormat.format(date)
    } catch (e: ParseException) {
        Log.e("datehandler", e.message.toString())
        null
    }
}

fun timeAfter (timestamp: Timestamp?, type: String, range: Int): Timestamp? {
    return try {
        val date: Date? = timestamp?.toDate()
        val calendar = Calendar.getInstance()
        calendar.time = date
        if (type == "month") {
            calendar.add(Calendar.MONTH, range) // Thêm range thang
        } else if (type == "year") {
            calendar.add(Calendar.YEAR, range) // Thêm range năm
        }
        Timestamp( calendar.time )
    } catch (e: ParseException) {
        null
    }
}