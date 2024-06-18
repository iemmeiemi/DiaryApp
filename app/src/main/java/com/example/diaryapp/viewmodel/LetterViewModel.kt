package com.example.diaryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diaryapp.data.Diary
import com.example.diaryapp.data.Letter
import com.example.diaryapp.data.Result
import com.example.diaryapp.data.repositories.LetterRepository
import com.example.diaryapp.di.FireBaseInjection
import com.google.firebase.Timestamp
import kotlinx.coroutines.launch

class LetterViewModel: ViewModel() {

    private val firebaseAuth = FireBaseInjection.authInstance()
    private val currentUser = firebaseAuth.currentUser
    private val userEmail = currentUser?.email
    private val letterRepository: LetterRepository

    init {
        letterRepository = LetterRepository(
            FireBaseInjection.instance()
        )
    }

    private val _CRUDLetterResult = MutableLiveData<Result<Boolean>>()
    val CRUDLetterResult: LiveData<Result<Boolean>> get() = _CRUDLetterResult

    private val _letters = MutableLiveData<List<Letter>>()
    val letters: LiveData<List<Letter>> get() = _letters

    private val _lettersInOneDay = MutableLiveData<List<Letter>>()
    val lettersInOneDay: LiveData<List<Letter>> get() = _lettersInOneDay


    fun createLetter(letter: Letter) {
        viewModelScope.launch {
            _CRUDLetterResult.value = letterRepository.createLetter(userEmail.toString(), letter)
        }
    }

    fun getLetters() {
        viewModelScope.launch {
            when (val result = letterRepository.getAllLetter(userEmail.toString())) {
                is Result.Success -> {
                    _letters.value = result.data as List<Letter>
                }
                is Error -> {
                    //Log.e("eror", )
                }
                else -> {}
            }
        }
    }

    fun getLettersInOneDay(createdAt: Timestamp) {
        viewModelScope.launch {
            when(val result = letterRepository.getLettersInOneDay(createdAt, userEmail.toString())) {
                is Result.Success -> {
                    _lettersInOneDay.value = result.data as List<Letter>
                }
                is Result.Error -> {

                }
                else -> {}
            }
        }
    }


    fun updateLetter(letter: Letter) {
        viewModelScope.launch {
            if (letter.id.isNullOrEmpty() || userEmail.isNullOrEmpty() ) {
                _CRUDLetterResult.value = Result.Error(Exception("There's a lack in software!"))
            } else {
                _CRUDLetterResult.value =
                    letterRepository.updateLetter( userEmail.toString(), letterId = letter.id, letter)
            }
        }
    }

    fun softDeleteLetter(letter: Letter) {
        viewModelScope.launch {
            if (letter.id.isNullOrEmpty() || userEmail.isNullOrEmpty() ) {
                _CRUDLetterResult.value = Result.Error(Exception("There's a lack in software!"))
            } else {
                _CRUDLetterResult.value = letterRepository.softDeleteLetter( userEmail, letter )
            }
        }
    }
}