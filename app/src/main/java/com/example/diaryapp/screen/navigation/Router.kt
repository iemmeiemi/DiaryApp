package com.example.diaryapp.screen.navigation


interface Router {
    fun goSignUp()
    fun goLogin()
    fun goDiary()
    fun goNotification()
    fun goSettings()
    fun goProfile()
    fun goSplash()
    fun goHome()

    fun goBack()
    fun <T : Any> getArgs(tag: String): T?
}