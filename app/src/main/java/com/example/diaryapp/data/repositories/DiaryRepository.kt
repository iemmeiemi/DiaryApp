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
    private suspend fun saveDiaryToFirestore(userEmail:String, diary: Diary) {
        firestore.collection("users").document(userEmail.toString())
            .collection("diaries").document().set(diary).await()
    }

    override suspend fun createDiary( userEmail: String, diary: Diary): Result<Boolean>? =
        try {
            val document = firestore.collection("users").document()
                .collection("diary").document()
            diary.id = document.id
            saveDiaryToFirestore(userEmail, diary)
            Result.Success(true )
        } catch(e: Exception) {
            Result.Error(e)
        }

    override suspend fun updateDiary(diaryId: String, diary: Diary) : Result<Boolean>? =
        try {
            firestore.collection("users").document("email")
                .collection("diaries").document(diaryId).set(diary).await()
            Result.Success(true )
        } catch(e: Exception) {
            Result.Error(e)
        }


    suspend fun deleteDiary() {

    }

    override suspend fun getDiaries(userEmail: String ) : List<Diary> {
        Log.i("reporun", "intothe repo")

        val varDiaries = firestore.collection("users").document(userEmail)
            .collection("diaries")
        return try {
            val documents = varDiaries.get().await()
            val diaries = mutableListOf<Diary>()
            if(documents.isEmpty) {
                emptyList()
            } else {
                Log.e(" repo", "doc")
                for (document in documents) {
                    val diary = document.toObject(Diary::class.java)
                    diaries.add(diary)
                }
                diaries
            }
        } catch (exception: Exception) {
            // Xử lý bất kỳ ngoại lệ nào xảy ra trong quá trình lấy dữ liệu
            Log.e("error repo", "Lỗi khi lấy danh sách nhật ký: ${exception.message}")
            emptyList()
        }
    }


    override suspend fun getAllDiary(userEmail: String): Result<Boolean>? {
        TODO("Not yet implemented")
    }


//    suspend fun getAllDiary(userEmail: String) : Result<Boolean>? =
//        try {
//            val querySnapshot = firestore.collection("users").document(userEmail)
//                .collection("diaries").get().await()
//            for (document in querySnapshot.documents) {
//                val diary = document.toObject<Diary>()
//                diary?.let {
//                    diaries.add(it)
//                }
//            }
//            Result.Success( true )
//        } catch(e: Exception) {
//            Result.Error(e)
//        }

    override suspend fun getOneDiary() {

    }

    suspend fun getDiaryofMonth() {

    }
}


