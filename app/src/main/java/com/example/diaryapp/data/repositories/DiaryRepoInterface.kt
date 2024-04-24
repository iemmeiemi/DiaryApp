package com.example.diaryapp.data.repositories

import com.example.diaryapp.data.Diary
import com.example.diaryapp.data.Result
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.CompletableFuture

interface DiaryRepoInterface {

    suspend fun createDiary( userEmail: String, diary: Diary): Result<Boolean>?
    suspend fun updateDiary(diaryId: String, diary: Diary) : Result<Boolean>?
    suspend fun getDiaries(userEmail: String ) : List<Diary>
    suspend fun getAllDiary(userEmail: String) : Result<Boolean>?
    suspend fun getOneDiary()
}