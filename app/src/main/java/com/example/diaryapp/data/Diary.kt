package com.example.diaryapp.data

import android.net.Uri
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale


data class Diary(
    var id: String = "",
    var title: String,
    var content: String,
    var mood: Mood? = null,
    var images: List<String?> ,
    var star: Boolean = false,
    var delete: Boolean = false,
    @ServerTimestamp
    var createdAt: Timestamp? = null
) {
    //have to have to create, have no idea why there is dèfault constructor but i still have to do this
    constructor() : this("", "", "", null, emptyList(),false, false, null)
}
