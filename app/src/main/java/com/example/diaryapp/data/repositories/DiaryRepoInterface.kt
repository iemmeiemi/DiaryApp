package com.example.diaryapp.data.repositories

import com.example.diaryapp.data.Diary
import com.example.diaryapp.data.Result
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.CompletableFuture

interface DiaryRepoInterface {

    suspend fun createDiary( userEmail: String, diary: Diary): Result<Boolean>?
    suspend fun updateDiary(diaryId: String, diary: Diary) : Result<Boolean>?
    suspend fun softDeleteDiary(diaryId: String, diary: Diary): Result<Boolean>?
    suspend fun deleteDiary(diaryId: String): Result<Boolean>?
    suspend fun getAllDiary(userEmail: String): Result<List<Diary>>
    suspend fun getOneDiary()
}