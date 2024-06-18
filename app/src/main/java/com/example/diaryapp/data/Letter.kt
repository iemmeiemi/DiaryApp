package com.example.diaryapp.data

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp

data class Letter(
    val id: String,
    val title: String,
    val content: String,
    var images: List<String?>,
    var isDeleted: Boolean = false,
    var sendedAt: Timestamp? = null,
    var createdDate: String? = null,

    @ServerTimestamp
    var createdAt: Timestamp? = null,

) {

    constructor() : this("", "", "", emptyList(),false, null, null)

}
