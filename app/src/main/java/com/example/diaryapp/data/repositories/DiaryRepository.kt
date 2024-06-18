package com.example.diaryapp.data.repositories

import android.util.Log
import com.example.diaryapp.data.Diary
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.example.diaryapp.data.Result
import com.example.diaryapp.network.firebaseStorage
import com.google.firebase.firestore.Query


class DiaryRepository(
    private val firestore: FirebaseFirestore
): DiaryRepoInterface {

    var diaryCollection = firestore.collection("users").document("email")
        .collection("diaries")

    private suspend fun saveDiaryToFirestore(userEmail: String, diary: Diary) {
        firestore.collection("users").document(userEmail).collection("diaries").document().set(diary).await()
    }

    override suspend fun createDiary(userEmail: String, diary: Diary): Result<Boolean>? =
        try {
            if (diary.images.isNotEmpty()) {
                Log.e("hahahahahah", diary.images.isNotEmpty().toString())
                diary.images = firebaseStorage().saveAndGetLink(diary.images, userEmail, "images")
                Log.e("hahahahahah", diary.images.isNotEmpty().toString())
            }
            saveDiaryToFirestore(userEmail, diary)
            Log.e("here", "saving")
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun updateDiary(userEmail: String, diaryId: String, diary: Diary): Result<Boolean>? =
        try {
            firestore.collection("users").document(userEmail).collection("diaries").document(diaryId).set(diary)
                .addOnSuccessListener {}
                .addOnFailureListener { e ->
                    throw Exception(e)
                }
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun softDeleteDiary(userEmail: String, diary: Diary): Result<Boolean>? =
        try {
            diary.delete = true
            firestore.collection("users").document(userEmail).collection("diaries").document(diary.id).set(diary)
                .addOnSuccessListener { }
                .addOnFailureListener { e -> throw Exception(e) }
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun deleteDiary(diary: Diary): Result<Boolean>? =
        try {
            diary.delete=true
            diaryCollection.document(diary.id).delete()
                .addOnSuccessListener { }
                .addOnFailureListener { e -> throw Exception(e) }
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }



    override suspend fun getAllDiary(userEmail: String): Result<List<Diary>> =
        try {
            Log.i("chekc", "getAllDiary: " +userEmail)
            val querySnapshot = firestore.collection("users").document(userEmail)
                .collection("diaries")
                .whereEqualTo("delete", false)
                .orderBy("createdAt", Query.Direction.ASCENDING)
                .get().await()
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

    //may not use
    override suspend fun getDiariesInOneDay(date: String, userEmail: String) : Result<List<Diary>> =
        try {
            val querySnapshot = firestore.collection("users").document(userEmail)
                .collection("diaries")
                .whereEqualTo("createdAt", date)
                .get().await()
            val diaries = querySnapshot.documents.map { document ->
                document.toObject(Diary::class.java)!!.copy(id = document.id)
            }
            Log.i("chekc", "getONeDiaryInonedate: " + diaries.isEmpty())
            Result.Success(diaries)
        } catch (e: Exception) {
            Result.Error(e)
        }

}


