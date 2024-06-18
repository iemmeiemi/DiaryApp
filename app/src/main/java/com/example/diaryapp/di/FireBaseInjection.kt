package com.example.diaryapp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object FireBaseInjection {

    private val instance: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    private val authInstance: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    fun authInstance(): FirebaseAuth {
        return authInstance
    }



    fun instance(): FirebaseFirestore {
        return instance
    }

}