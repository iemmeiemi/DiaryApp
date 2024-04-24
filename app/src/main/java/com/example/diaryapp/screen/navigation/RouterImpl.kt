package com.example.diaryapp.screen.navigation


import android.util.Log
import androidx.navigation.NavHostController
import com.example.diaryapp.screen.navigation.Routes.ROUTE_HOME

//class implement các hàm trong interface, dùng để navigate

class RouterImpl(
    private val navController: NavHostController,
    private val startDestination: String = ROUTE_HOME
) : Router {


    override fun goHome() {
        navigate(Screen.HomeScreen, removeFromHistory = true, singleTop = true)
    }

//    override fun goHistory() {
//        navigate(Screen.History)
//    }



    override fun goSettings() {
        navigate(Screen.SettingScreen)
    }

    override fun goSignUp() {
        navigate(Screen.SignupScreen)
    }

    override fun goLogin() {
        navigate(Screen.LoginScreen)
    }

    override fun goDiary() {
        navigate(Screen.DiaryScreen)
    }

    override fun goNotification() {
        navigate(Screen.NotificationScreen)
    }

    override fun goProfile() {
        navigate(Screen.ProfileScreen)
    }

    override fun goSplash() {
        navigate(Screen.SplashScreen, true)
    }

    override fun goBack() {
        Log.i("navigate","sdfdsfsf")
        navController.apply {
            navigateUp()


            navigate(startDestination) {
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    private fun navigate(
        screen: Screen,
        removeFromHistory: Boolean = false,
        singleTop: Boolean = false
    ) {
        navController.apply {
            navigate(screen.route) {
                if (removeFromHistory) {
                    if (singleTop) {
                        popUpTo(Screen.HomeScreen.route)
                    } else {
                        popUpTo(0) {
                            saveState = false
                        }
                    }

                } else {
                    restoreState = true
                }
                launchSingleTop = singleTop
            }
        }
    }

    private fun checkArgsAndNavigate(it: Any?, screen: Screen): () -> Unit = {
        it?.let {
            navController.previousBackStackEntry?.savedStateHandle?.set(screen.tag, it)
        }
        navigate(screen)
    }

    override fun <T : Any> getArgs(tag: String): T? {
        return try {
            navController.previousBackStackEntry?.arguments?.get(tag) as T?
        } catch (ex: Exception) {
            null
        }
    }

}