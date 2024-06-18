package com.example.diaryapp.data.repositories

import android.util.Log
import com.example.diaryapp.data.Letter
import com.example.diaryapp.data.Result
import com.example.diaryapp.data.TimeSchedule
import com.example.diaryapp.network.firebaseStorage
import com.example.diaryapp.utils.convertFirebaseTimestampToString
import com.example.diaryapp.utils.getDate
import com.example.diaryapp.utils.timeAfter
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import java.util.Date

class LetterRepository(
    private val firestore: FirebaseFirestore
) {

    private suspend fun saveLetterToFirestore(userEmail: String, letter: Letter) {
        firestore.collection("users").document(userEmail).collection("letters").document().set(letter).await()
    }

    suspend fun createLetter(userEmail: String, letter: Letter): Result<Boolean>? =
        try {
            if (letter.images.isNotEmpty()) {
                letter.images = firebaseStorage().saveAndGetLink(letter.images, userEmail, "images")
                Log.e("hahahahahah", letter.images.isNotEmpty().toString())
            }
            saveLetterToFirestore(userEmail, letter)
            Log.e("here", "saving")
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }

    fun updateLetter(userEmail: String, letterId: String, letter: Letter): Result<Boolean>? =
        try {
            firestore.collection("users").document(userEmail).collection("letters").document(letterId).set(letter)
                .addOnSuccessListener {}
                .addOnFailureListener { e ->
                    throw Exception(e)
                }
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }

    fun softDeleteLetter(userEmail: String, letter: Letter): Result<Boolean>? =
        try {
            letter.isDeleted = true
            firestore.collection("users").document(userEmail).collection("letters").document(letter.id).set(letter)
                .addOnSuccessListener { }
                .addOnFailureListener { e -> throw Exception(e) }
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }

     suspend fun getAllLetter(userEmail: String): Result<List<Letter>> =
        try {
            val querySnapshot = firestore.collection("users").document(userEmail)
                .collection("letters")
                .whereEqualTo("isDeleted", false)
                .orderBy("createdAt", Query.Direction.ASCENDING)
                .get().await()
            val letters = querySnapshot.documents.map { document ->
                document.toObject(Letter::class.java)!!.copy(id = document.id)
            }

            Result.Success(letters)
        } catch (e: Exception) {
            Result.Error(e)
        }

     suspend fun getLettersInOneDay(createdAt: Timestamp, userEmail: String) : Result<List<Letter>> =
        try {
            var letters: List<Letter> = listOf()

            val querySnapshot = firestore.collection("users").document(userEmail)
                .collection("letters")
                .whereGreaterThanOrEqualTo("createdDate", timeAfter(createdAt, TimeSchedule.MinusOneDay)!!.toDate()) //timeForQuery(createdAt)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    // Xử lý các tài liệu được trả về
                    letters = querySnapshot.documents.map { document ->
                        document.toObject(Letter::class.java)!!.copy(id = document.id)
                    }
                }
                //Log.i("chekc", "querySnapshot.documents.size: ${)}")
            Log.i("chekc", "get ONe Letter In one date: " + letters.isNullOrEmpty())
            Result.Success(letters)
        } catch (e: Exception) {
            Result.Error(e)
        }
}