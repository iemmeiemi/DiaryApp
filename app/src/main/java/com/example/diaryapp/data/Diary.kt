package com.example.diaryapp.data

import com.google.firebase.Timestamp


data class Diary(
    var id: String = "",
    val title: String,
    val content: String,
    val star: Boolean = false,
    val delete: Boolean = false,
    //val createdAt: Timestamp = Timestamp.now()
) {
    constructor() : this("", "", "", false, false)
}
