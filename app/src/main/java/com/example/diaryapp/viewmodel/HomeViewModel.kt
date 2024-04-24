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
import kotlinx.coroutines.future.await
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val currentUser = firebaseAuth.currentUser
    private val userEmail = currentUser?.email



    private val diaryRepository: DiaryRepository
    private val _diaries = MutableLiveData<List<Diary>>()
    val diaries: LiveData<List<Diary>> get() = _diaries


    init {
        diaryRepository = DiaryRepository(
            FireBaseInjection.instance()
        )
    }

    fun getDiaries() {

        viewModelScope.launch {
            if (userEmail != null) {
                Log.e("email", userEmail)
            }
            val list : List<Diary> = diaryRepository.getDiaries(userEmail.toString())
            _diaries.value = list
        }
    }
}