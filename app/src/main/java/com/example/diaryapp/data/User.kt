package com.example.diaryapp.data



sealed class roleEnum(
    val roleAuthorization: String
) {
    object admin:roleEnum("admin")
    object normie:roleEnum("normie")
    object vip:roleEnum("vip")
}

data class User(
    var id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val diaries: List<Diary> = listOf(),
//    val goals: List<Diary>,
//    val letters: List<Diary>,
    val role: String = roleEnum.normie.roleAuthorization,
    val delete: Boolean = false,
)