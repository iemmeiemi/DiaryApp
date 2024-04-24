package com.example.diaryapp.data

sealed class Result<out T>{
    data class Success<out T>(val data: Any) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}