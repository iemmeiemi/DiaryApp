package com.example.diaryapp.data

import android.graphics.drawable.Icon
import com.google.firebase.Timestamp

sealed class Mood(
    var name: String,
    val description: String,
    val icon: String,
    val delete: Boolean = false,
    val moodArray: Array<Mood> = arrayOf(Happy, Sad, Avg)
) {
    object Happy: Mood("happy", "Happy", "https://firebasestorage.googleapis.com/v0/b/diaryapp-29d40.appspot.com/o/icons%2Fmoods%2Fhappyicon.svg?alt=media&token=8b7f681b-3ebf-407b-9a60-d7c97cb5fd3f" )
    object Sad: Mood("Sad", "Sad", "https://firebasestorage.googleapis.com/v0/b/diaryapp-29d40.appspot.com/o/icons%2Fmoods%2Fsadicon.svg?alt=media&token=5aa68589-90ac-4410-bf54-57ba9dd8888d" )
    object Avg: Mood("Normal", "Just an average feeling", "https://firebasestorage.googleapis.com/v0/b/diaryapp-29d40.appspot.com/o/icons%2Fmoods%2Fnormalicon.svg?alt=media&token=f7e8a084-69d0-4708-b2d0-b1bba73b4224")
    companion object {
        val moodArray: Array<Mood> = arrayOf(Happy, Sad, Avg)
    }
}
/* Image loading
AsyncImage(
    model = "https://example.com/image.jpg",
    contentDescription = "Translated description of what the image contains"
)
 */
/*
data class Mood(
    var name: String,
    val description: String,
    val delete: Boolean = false,
    val createdAt: Timestamp = Timestamp.now()
) {
    constructor() : this("", "")
}
*/