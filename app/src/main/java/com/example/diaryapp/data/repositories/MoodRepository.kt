package com.example.diaryapp.data.repositories

import com.example.diaryapp.data.Diary
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class MoodRepository(
    private val firestore: FirebaseFirestore
) {
    var moodCollection = firestore.collection("moods")

    private suspend fun saveMoodToFirestore(userEmail: String, diary: Diary) {
        moodCollection.document().set(diary).await()
    }
}