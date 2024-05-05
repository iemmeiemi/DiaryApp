package com.example.diaryapp.viewmodel

import android.util.Log
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
import java.lang.Exception


class DiaryViewModel: ViewModel() {

    val firebaseAuth = FirebaseAuth.getInstance()
    val currentUser = firebaseAuth.currentUser
    val userEmail = currentUser?.email

    private val diaryRepository: DiaryRepository

    private val _createDiaryResult = MutableLiveData<Result<Boolean>>()
    val createDiaryResult: LiveData<Result<Boolean>> get() = _createDiaryResult

    private val _updateDiaryResult = MutableLiveData<Result<Boolean>>()
    val updateDiaryResult: LiveData<Result<Boolean>> get() = _updateDiaryResult

    private val _diaries = MutableLiveData<List<Diary>>()
    val diaries: LiveData<List<Diary>> get() = _diaries


    init {
        diaryRepository = DiaryRepository(
            FireBaseInjection.instance()
        )
        getDiaries()
    }

    fun createDiary(diary: Diary) {
        viewModelScope.launch {
            _createDiaryResult.value = diaryRepository.createDiary(userEmail.toString(), diary)
        }
    }

    fun getDiaries() {
        viewModelScope.launch {
            when (val result = diaryRepository.getAllDiary(userEmail.toString())) {
                is Result.Success -> _diaries.value = result.data as List<Diary>
                is Error -> {

                }

                else -> {}
            }
//            val list : List<Diary> = diaryRepository.getDiaries(userEmail.toString())
//            _diaries.value = list
        }
    }

    fun updateDiary(diary: Diary) {
        viewModelScope.launch {
            if (diary.id.isBlank()) {
                _updateDiaryResult.value = Result.Error(Exception("There's a lack in software!"))
            } else {
                _updateDiaryResult.value = diaryRepository.updateDiary(diaryId = diary.id, diary)
            }
        }
    }


}
