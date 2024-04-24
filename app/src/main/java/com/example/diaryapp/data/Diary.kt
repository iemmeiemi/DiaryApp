package com.example.diaryapp.data

data class Diary(
    var id: String = "",
    val title: String,
    val content: String,
    val star: Boolean = false,
    val delete: Boolean = false,
) {
    constructor() : this("", "", "", false, false)
}
