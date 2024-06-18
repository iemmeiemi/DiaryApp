package com.example.diaryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diaryapp.data.Diary
import com.example.diaryapp.data.repositories.UserRepository
import com.example.diaryapp.di.FireBaseInjection
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import com.example.diaryapp.data.Result
import com.example.diaryapp.data.User
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class AuthViewModel : ViewModel() {
    private val userRepository: UserRepository

    private val firebaseAuth = Firebase.auth
    private val currentUser = firebaseAuth.currentUser
    private val userEmail = currentUser?.email
    //private val user = currentUser?.metadata

    private val _userInfo = MutableLiveData<User?>()
    val userInfo: LiveData<User?> get() = _userInfo

    private val _authResult = MutableLiveData<Result<Boolean>?>()
    val authResult: MutableLiveData<Result<Boolean>?> get() = _authResult


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

    fun signOut(email: String, password: String, firstName: String, lastName: String) {
        viewModelScope.launch {
            firebaseAuth.signOut()
            _authResult.value = null
        }
    }

    fun autoLogin() {
        viewModelScope.launch {
            if ( currentUser != null ) {
                _authResult.value = Result.Success(true)
            } else {
                _authResult.value = Result.Success(false)
            }
        }
    }

    fun getUserInfo() {
        viewModelScope.launch {
            when (val result = userEmail?.let { userRepository.getUser(it) }) {
                is Result.Success -> _userInfo.value = result.data as User
                is Error -> {
                    //Log.e("eror", )
                }
                else -> {}
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authResult.value = userRepository.login(email, password)
        }
    }
}