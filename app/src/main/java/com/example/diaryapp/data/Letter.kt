package com.example.diaryapp.data

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp

data class Letter(
    val id: String,
    val title: String,
    val content: String,
    val isDeleted: Boolean,

    @ServerTimestamp
    var createdAt: Timestamp? = null,
    var sendedAt: Timestamp? = null
) {

}
