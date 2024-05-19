package com.example.diaryapp.data

import com.google.firebase.Timestamp

data class Mood(
    var name: String,
    val description: String,
    val delete: Boolean = false,
    val createdAt: Timestamp = Timestamp.now()
) {
    constructor() : this("", "")
}
