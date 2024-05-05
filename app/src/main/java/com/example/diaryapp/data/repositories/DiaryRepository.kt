package com.example.diaryapp.data.repositories

import android.util.Log
import com.example.diaryapp.data.Diary
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.example.diaryapp.data.Result
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.toObject
import java.util.Locale
import java.util.concurrent.CompletableFuture


class DiaryRepository(
    private val firestore: FirebaseFirestore
): DiaryRepoInterface {

    var diaryCollection = firestore.collection("users").document("email")
        .collection("diaries")

    private suspend fun saveDiaryToFirestore(userEmail: String, diary: Diary) {
        diaryCollection.document().set(diary).await()
    }

    override suspend fun createDiary(userEmail: String, diary: Diary): Result<Boolean>? =
        try {
            val document = firestore.collection("users").document(userEmail)
                .collection("diaries").document()
            diary.id = document.id
            saveDiaryToFirestore(userEmail, diary)
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun updateDiary(diaryId: String, diary: Diary): Result<Boolean>? =
        try {
            diaryCollection.document(diaryId).set(diary)
                .addOnSuccessListener {}
                .addOnFailureListener { e ->
                    throw Exception(e)
                }
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun softDeleteDiary(diaryId: String, diary: Diary): Result<Boolean>? =
        try {

            diaryCollection.document(diaryId).set(diary)
                .addOnSuccessListener { }
                .addOnFailureListener { e -> throw Exception(e) }
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun deleteDiary(diaryId: String): Result<Boolean>? =
        try {
            diaryCollection.document(diaryId).delete()
                .addOnSuccessListener { }
                .addOnFailureListener { e -> throw Exception(e) }
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }



    override suspend fun getAllDiary(userEmail: String): Result<List<Diary>> =
        try {
            val querySnapshot = firestore.collection("users").document(userEmail)
                .collection("diaries").get().await()
            val diaries = querySnapshot.documents.map { document ->
                document.toObject(Diary::class.java)!!.copy(id = document.id)
            }
            Log.i("chekc", "getAllDiary: " + diaries.isEmpty())
            Result.Success(diaries)
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun getOneDiary() {

    }

}


