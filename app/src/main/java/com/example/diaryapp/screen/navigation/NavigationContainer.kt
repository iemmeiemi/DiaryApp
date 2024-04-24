package com.example.diaryapp.screen.navigation

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.diaryapp.screen.screens.DiaryScreen
import com.example.diaryapp.screen.screens.HomeScreen
import com.example.diaryapp.screen.screens.LoginScreen
import com.example.diaryapp.screen.screens.NotificationScreen
import com.example.diaryapp.screen.screens.ProfileScreen
import com.example.diaryapp.screen.screens.SettingScreen
import com.example.diaryapp.screen.screens.SignUpScreen
import com.example.diaryapp.screen.screens.SplashScreen

//Navigation để navigate giữa các screen
//bắt đầu app sẽ là splashScreen -> homescreen
@Composable
fun navControllerContainer(
    router: Router,
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SignupScreen.route
    ) {
        /*
        //định nghĩa các route và gọi hàm UI của từng screen trong đó
        composable(Screen.LoginScreen.route) {
            LoginScreen(//router)
        }
        composable(Screen.SignupScreen.route) {
            Log.i("run", "runSignUpScreen")
            SignUpScreen(router)
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen(paddingValues, router)
        }

        //thêm hàm back vào Splash Screeen là quay lại Home
        //startdestination chạy splashscreen
        composable(Screen.SplashScreen.route) {
            Log.i("run", "runSplashScreen1")
            SplashScreen(

//                goBack = {
//                    startDestination.value = Screen.HomeScreen.route
//                },
                router = router
            )
        }

        composable(Screen.NotificationScreen.route) {
            NotificationScreen(paddingValues)
        }
        composable(Screen.SettingScreen.route) {
            SettingScreen(paddingValues)
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(paddingValues)
        }
        composable(Screen.DiaryScreen.route) {
            DiaryScreen(paddingValues)
        }
        */
    }
}