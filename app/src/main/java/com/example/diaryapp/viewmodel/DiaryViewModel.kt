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
import com.example.diaryapp.di.FireBaseInjection.authInstance
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch


class DiaryViewModel: ViewModel() {

    private val firebaseAuth = authInstance()
    private val currentUser = firebaseAuth.currentUser
    private val userEmail = currentUser?.email


    private val diaryRepository: DiaryRepository

    //thay đổi thành StateFlow, LiveData lỗi thời r
    private val _CRUDDiaryResult = MutableLiveData<Result<Boolean>>()
    val CRUDDiaryResult: LiveData<Result<Boolean>> get() = _CRUDDiaryResult

    private val _diaries = MutableLiveData<List<Diary>>()
    val diaries: LiveData<List<Diary>> get() = _diaries

    private val _diaryInOneDay = MutableLiveData<Diary>()
    val diaryInOneDay: LiveData<Diary> get() = _diaryInOneDay

    private val _displayDiaries = MutableLiveData<List<Diary>>()
    val displayDiaries: LiveData<List<Diary>> get() = _displayDiaries

    init {
        diaryRepository = DiaryRepository(
            FireBaseInjection.instance()
        )
//        getDiaries()
    }

    fun display(type: String) {
        if (type == "one") {
            _displayDiaries.value = diaryInOneDay.value?.let { listOf(it) }
        } else if (type == "all") {
            _displayDiaries.value = diaries.value
        }
    }


    fun createDiary(diary: Diary) {
        viewModelScope.launch {
            _CRUDDiaryResult.value = diaryRepository.createDiary(userEmail.toString(), diary)
        }
    }

    fun getDiaries() {
        viewModelScope.launch {
            when (val result = diaryRepository.getAllDiary(userEmail.toString())) {
                is Result.Success -> {
                    _diaries.value = result.data as List<Diary>
                }
                is Error -> {
                    //Log.e("eror", )
                }
                else -> {}
            }
        }
    }

    /*fun getDiariesInOneDay(date: String) {
        viewModelScope.launch {
            Log.e("func2" , diaries.value.toString())
            _diaryInOneDay.value = diaries.value?.find { d -> d.getDate() == date }
            _diaryInOneDay.value?.getDate()?.let { Log.e("func", it) }
            Log.e("func2" , date)
        }
    }*/

    fun updateDiary(diary: Diary) {
        viewModelScope.launch {
            if (diary.id.isNullOrEmpty() || userEmail.isNullOrEmpty() ) {
                _CRUDDiaryResult.value = Result.Error(Exception("There's a lack in software!"))
            } else {
                _CRUDDiaryResult.value =
                    diaryRepository.updateDiary( userEmail.toString(), diaryId = diary.id, diary)
            }
        }
    }

    fun softDeleteDiary(diary: Diary) {
        viewModelScope.launch {
            if (diary.id.isNullOrEmpty() || userEmail.isNullOrEmpty() ) {
                _CRUDDiaryResult.value = Result.Error(Exception("There's a lack in software!"))
            } else {
                _CRUDDiaryResult.value = diaryRepository.softDeleteDiary( userEmail, diary )
            }
        }
    }
}
