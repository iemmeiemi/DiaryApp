package com.example.diaryapp.screen.navigation

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.diaryapp.screen.screens.DiaryScreen
import com.example.diaryapp.screen.screens.DiarySheetScreen
import com.example.diaryapp.screen.screens.HomeScreen
import com.example.diaryapp.screen.screens.LoginScreen
import com.example.diaryapp.screen.screens.NotificationScreen
import com.example.diaryapp.screen.screens.ProfileScreen
import com.example.diaryapp.screen.screens.SignUpScreen
import com.example.diaryapp.viewmodel.AuthViewModel
import com.example.diaryapp.viewmodel.DiaryViewModel
import com.example.diaryapp.data.Result
import com.example.diaryapp.screen.screens.AllDiariesScreen
import com.example.diaryapp.viewmodel.HomeViewModel


@Composable
fun NavigationGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    context: Context,
    authViewModel: AuthViewModel = viewModel(),
    homeViewModel: HomeViewModel = viewModel(),
    diaryViewModel: DiaryViewModel = viewModel(),

) {
    authViewModel.autoLogin()
    var startDestination: String = Screen.LoginScreen.route
    authViewModel.authResult.value?.let { result ->
        when (result) {
            is Result.Success<Boolean> -> {
                if (result.data == true) {
                    // Do something when authResult is true
                    startDestination = Screen.HomeScreen.route
                } else {
                    // Do something when authResult is false
                }
            }
            is Result.Error -> {
                // Handle the error case
            }
            else -> {}
        }
    }
    Log.e("log", startDestination)

    NavHost(
        navController = navController,
        startDestination = startDestination

    ) {
        composable(Screen.SignupScreen.route) {
            Log.i("run", "signup")
            SignUpScreen(
                authViewModel,
                navController,
                context = context,
            )
        }
        composable(Screen.LoginScreen.route) {
            Log.i("run", "login")
            LoginScreen(
                authViewModel,
                navController,
                context = context
            )
        }

        composable(Screen.HomeScreen.route) {
            Log.i("run", "home")
            HomeScreen(
                diaryViewModel = diaryViewModel,
                authViewModel = authViewModel,
                navController = navController,
                paddingValues = paddingValues
            )
        }
        composable(Screen.DiaryScreen.route) {
            DiaryScreen( diaryViewModel, navController, context, paddingValues )
        }

        composable(Screen.AllDiariesScreen.route) {
            Log.i("run", "login")
            AllDiariesScreen(
                navController,
                paddingValues,
                context,
                diaryViewModel
            )
        }

        composable(Screen.ProfileScreen.route) {
            ProfileScreen(navController, paddingValues, authViewModel)
        }

        composable(Screen.NotificationScreen.route) {
            NotificationScreen( paddingValues = paddingValues, navController = navController )
        }

        composable("${Screen.DiaryScreen.route}/{diaryId}") {
            val diaryId = it.arguments?.getString("diaryId")
            DiaryScreen(
                diaryViewModel, navController, context, paddingValues,
                diaryId = diaryId.toString(),
            )
        }

        composable("${Screen.DiarySheetScreen.route}/{diaryId}") {
            val diaryId = it.arguments?.getString("diaryId")
            DiarySheetScreen(
                diaryId = diaryId,
                diaryViewModel = diaryViewModel,
                navController,
                context,
                paddingValues
            )
        }
    }
}