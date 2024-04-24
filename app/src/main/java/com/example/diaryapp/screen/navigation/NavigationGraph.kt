package com.example.diaryapp.screen.navigation

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.diaryapp.data.Diary
import com.example.diaryapp.screen.screens.DiaryScreen
import com.example.diaryapp.screen.screens.DiarySheetScreen
import com.example.diaryapp.screen.screens.HomeScreen
import com.example.diaryapp.screen.screens.LoginScreen
import com.example.diaryapp.screen.screens.SignUpScreen
import com.example.diaryapp.viewmodel.AuthViewModel
import com.example.diaryapp.viewmodel.DiaryViewModel
import com.example.diaryapp.viewmodel.HomeViewModel


@Composable
fun NavigationGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    homeViewModel: HomeViewModel,
    diaryViewModel: DiaryViewModel,
    paddingValues: PaddingValues,
    context: Context,
    finish: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ) {
        // Hàm xử lý sự kiện khi muốn pop màn hình hiện tại ra khỏi backStackEntry
        fun onPopScreen() {
            navController.popBackStack()
        }

        fun clearBackStackAndNavigateToHomeScreen(route: String) {
            navController.navigate(Screen.HomeScreen.route) {
                popUpTo(route) {
                    inclusive = true
                }
                launchSingleTop = true
            }
            navController.navigate(Screen.HomeScreen.route)
        }

        val onNavigateToHome = {
            navController.navigate(Screen.HomeScreen.route) {
                popUpTo(Screen.HomeScreen.route) {
                    inclusive = false
                }
                launchSingleTop = true
            }
        }

        val onNavigateToDiary = {
            navController.navigate(Screen.DiaryScreen.route)
        }

        val onNavigateToPremium = {
            navController.navigate(Screen.DiaryScreen.route)
        }

        val onNavigateToSpecificDiary: (String) -> Unit = {diaryId ->
            navController.navigate(Screen.DiarySheetScreen.route)
        }

        val onNavigateToDiaryForUpdate: ( diary: Diary) -> Unit = {diary ->
            navController.navigate("${Screen.DiaryScreen.route}?diaryId=${diary.id}&diaryTitle=${diary.title}&diaryContent=${diary.content}")
        }

        composable(Screen.SignupScreen.route) {
            Log.i("run", "signup")
            SignUpScreen(
                authViewModel,
                onNavigateToLogin = {  navController.navigate(Screen.LoginScreen.route) },
                context = context,
            ) {
                navController.navigate(Screen.LoginScreen.route) {
                    launchSingleTop = true
                }
            }
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                authViewModel,
                onNavigateToSignUp = { navController.navigate(Screen.SignupScreen.route) {
                    launchSingleTop = true
                } },
                context = context
            ) {
                clearBackStackAndNavigateToHomeScreen(Screen.LoginScreen.route)
            }
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen(
                homeViewModel = homeViewModel,
                navController = navController,
                onNavigateToDiary = onNavigateToDiary,
                onNavigateToPremium = onNavigateToPremium,
                finish = finish
            )
        }
        composable(Screen.DiaryScreen.route) {
            DiaryScreen( diaryViewModel, context, paddingValues ) {
                clearBackStackAndNavigateToHomeScreen(Screen.DiaryScreen.route)
            }
        }

        composable("${Screen.DiaryScreen.route}/diaryId={diaryId}&diaryTitle={diaryTitle}&diaryContent={diaryContent}") {
            val diary = listOf(
                navArgument("diaryId") { type = NavType.StringType },
                navArgument("diaryTitle") { type = NavType.StringType },
                navArgument("diaryContent") { type = NavType.StringType },
            )
            DiaryScreen(
                diaryViewModel, context, paddingValues,
                diaryId = diary.get(0).toString(),
                diaryTitle = diary.get(1).toString(),
                diaryContent = diary.get(2).toString(),
            ) {
                clearBackStackAndNavigateToHomeScreen(Screen.DiaryScreen.route)
            }
        }

        composable("${Screen.DiarySheetScreen.route}/{diaryId}") {
            val diaries = homeViewModel.diaries.value
            val diaryId = listOf(navArgument("diaryId") { type = NavType.StringType })
            val diary: Diary? = diaries?.find { d -> d.id == diaryId.get(0).toString() }
            DiarySheetScreen(
                diary = diary,
                onNavigateToDiaryForUpdate = onNavigateToDiaryForUpdate
            )
        }
    }
}