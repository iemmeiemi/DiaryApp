package com.example.diaryapp.utils

import android.util.Log
import com.example.diaryapp.data.TimeSchedule
import com.google.firebase.Timestamp
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

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

fun timeAfter (timestamp: Timestamp?, range: TimeSchedule): Timestamp? {
    return try {
        val date: Date? = timestamp?.toDate()
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.MONTH, range.rangeOfMonth) // Thêm range thang
        calendar.add(Calendar.YEAR, range.rangeOfYear) // Thêm range năm
        calendar.add(Calendar.DATE, range.rangeOfDay) // Thêm range ngay
        Timestamp( calendar.time )
    } catch (e: ParseException) {
        null
    }
}

fun timeForQuery(timestamp: Timestamp): String {

    val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH)
    val formattedDate = dateFormat.format(timestamp.toDate())

    return formattedDate // Output: Jun 16, 2024
}

fun convertFirebaseTimestampToString(timestamp: Timestamp): String {
    val dateFormat = SimpleDateFormat("MMMM d, yyyy 'at' hh:mm:ss a 'UTC+7'", Locale.US)
    dateFormat.timeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh")
    return dateFormat.format(timestamp.toDate())
}

