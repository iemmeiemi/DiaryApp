package com.example.diaryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diaryapp.data.Diary
import com.example.diaryapp.data.Result
import com.example.diaryapp.data.repositories.DiaryRepository
import com.example.diaryapp.di.FireBaseInjection
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch


class DiaryViewModel: ViewModel() {
    val firebaseAuth = FirebaseAuth.getInstance()
    val currentUser = firebaseAuth.currentUser
    val userEmail = currentUser?.email

    private val diaryRepository: DiaryRepository
    private val _diaryResult = MutableLiveData<Result<Boolean>>()
    val diaryResult: LiveData<Result<Boolean>> get() = _diaryResult
    private val _diaries = MutableLiveData<List<Diary>>()
    val diaries: LiveData<List<Diary>> get() = _diaries


    init {
        diaryRepository = DiaryRepository(
            FireBaseInjection.instance()
        )
    }

    fun createDiary(diary: Diary) {
        viewModelScope.launch {
            _diaryResult.value = diaryRepository.createDiary(userEmail.toString(), diary)
        }
    }


}
