package com.example.diaryapp.data.repositories

import com.example.diaryapp.data.Result

interface UserRepoInterface {
    suspend fun signUp(email: String,
               password: String,
               firstName: String,
               lastName: String
    ) : Result<Boolean>

    suspend fun login(
        email: String,
        password: String,
    ) : Result<Boolean>

}