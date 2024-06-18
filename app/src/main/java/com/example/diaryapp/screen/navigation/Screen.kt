package com.example.diaryapp.screen.navigation

import androidx.annotation.DrawableRes
import com.example.diaryapp.screen.navigation.Routes.fullScreenRoutes

object Routes {
    const val ROUTE_HOME = "ROUTE_HOME"
    const val ROUTE_SEARCH = "ROUTE_SEARCH"
    const val ROUTE_LIBS = "ROUTE_LIBS"
    const val ROUTE_PREMIUM = "ROUTE_PREMIUM"
    const val ROUTE_HOME_DETAILS = "ROUTE_HOME_DETAILS"
    const val ROUTE_SPLASH = "ROUTE_SPLASH"
    const val ROUTE_PERSONS = "ROUTE_PERSONS"
    const val ROUTE_PODCASTS = "ROUTE_PODCASTS"
    const val ROUTE_PLAYER_FULL = "ROUTE_PLAYER_FULL"
    const val ROUTE_NOTIFICATION = "ROUTE_NOTIFICATION"
    const val ROUTE_SETTINGS = "ROUTE_SETTINGS"
    const val ROUTE_HISTORY = "ROUTE_HISTORY"
    const val ROUTE_PROFILE = "ROUTE_PROFILE"

    val fullScreenRoutes = listOf(
        ROUTE_SPLASH,
        ROUTE_PERSONS,
        ROUTE_PODCASTS,
        ROUTE_PLAYER_FULL
    )
}

//enum các screen sẽ có trong project
sealed class Screen(
    val route: String,
    var tag: String = route,
    val title: String = "",
    @DrawableRes val icon: Int = 0
) {

    object LoginScreen:Screen("loginscreen")
    object SignupScreen:Screen("signupscreen")
    object HomeScreen:Screen("homescreen")
    object DiaryScreen:Screen("diaryscreen")
    object DiarySheetScreen:Screen("diarysheetscreen")
    object AllDiariesScreen:Screen("alldiariesscreen")
    object LetterScreen:Screen("letterscreen")
    object PremiumScreen:Screen("premiumscreen")
    object BillScreen:Screen("billscreen")
    object SplashScreen:Screen("splashscreen")
    object NotificationScreen:Screen("notificationscreen")
    object SettingScreen:Screen("settingscreen")
    object ProfileScreen:Screen("profilescreen")

    object ChatRoomsScreen:Screen("chatroomscreen")
    object ChatScreen:Screen("chatscreen")

    companion object {
        fun isFullScreen(route: String?): Boolean {
            return fullScreenRoutes.contains(route)
        }
    }
}