package com.example.diaryapp.data.repositories

import android.accounts.AuthenticatorDescription
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp

data class PremiumPackage(
    val id: String,
    val title: String,
    val description: String,
    val price: Int,
    val endedAt: Timestamp? = null,

    @ServerTimestamp
    val createdAt: Timestamp? = null,
)
