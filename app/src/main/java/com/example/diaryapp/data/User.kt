package com.example.diaryapp.data

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp
import org.json.JSONObject
import java.util.Date


sealed class roleEnum(
    val roleAuthorization: String
) {
    object admin:roleEnum("admin")
    object normie:roleEnum("normie")
    object vip:roleEnum("vip")
}

data class User(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val diaries: List<Diary> = listOf(),
    val notifications: List<Diary> = listOf(),
    val letters: List<Letter> = listOf(),
    val purchaseHistories : List<JSONObject> = listOf(),
    var stillVIP: Timestamp? = null,
    val role: String = roleEnum.normie.roleAuthorization,
    val delete: Boolean = false,
) {
    fun isVip(): Boolean {
        if(this.stillVIP!=null) {
            return this.stillVIP!!.toDate().before(Date())
        }
        return false
    }
}