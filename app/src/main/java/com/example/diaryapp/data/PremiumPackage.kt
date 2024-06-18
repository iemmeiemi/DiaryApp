package com.example.diaryapp.data

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp

data class PremiumPackage(
    val id: String,
    val title: String,
    val description: String,
    val price: Int,
    val type: String,
    val endedAt: Timestamp? = null,

    @ServerTimestamp
    val createdAt: Timestamp? = null,
)

sealed class PremiumSealedPacked(
    val id: String,
    val title: String,
    val description: String,
    val price: Int,
    val type: String,
) {
    object oneMonth : PremiumSealedPacked("onemonth", "One month Upgrade to VIP", " ", 20000, "premium")
    object oneYear : PremiumSealedPacked("oneyear", "One year Upgrade to VIP", " ", 200000, "premium")
    object sixMonth : PremiumSealedPacked("sixmonth", "Six month Upgrade to VIP", " ", 111000, "premium")

    //companion act like a static class in java, that can be use by external without creating an instance of the class contains them
    companion object {
        val premiumList: List<PremiumSealedPacked> = listOf(oneMonth, oneYear, sixMonth)
    }
}