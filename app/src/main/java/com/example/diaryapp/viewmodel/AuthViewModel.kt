package com.example.diaryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diaryapp.data.repositories.UserRepository
import com.example.diaryapp.di.FireBaseInjection
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import com.example.diaryapp.data.Result

class AuthViewModel : ViewModel() {
    private val userRepository: UserRepository
    private val _authResult = MutableLiveData<Result<Boolean>>()
    val authResult: LiveData<Result<Boolean>> get() = _authResult


    init {
        userRepository = UserRepository(
            FirebaseAuth.getInstance(),
            FireBaseInjection.instance()
        )
    }



    fun signUp(email: String, password: String, firstName: String, lastName: String) {
        viewModelScope.launch {
            _authResult.value = userRepository.signUp(email, password, firstName, lastName)
        }
    }


    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authResult.value = userRepository.login(email, password)
        }
    }
}