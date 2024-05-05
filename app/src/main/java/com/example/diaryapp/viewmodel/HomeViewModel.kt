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


}