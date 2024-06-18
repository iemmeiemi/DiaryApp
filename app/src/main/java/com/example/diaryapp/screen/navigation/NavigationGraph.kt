package com.example.diaryapp.screen.navigation

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.activity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.diaryapp.data.Result
import com.example.diaryapp.screen.screens.AllDiariesScreen
import com.example.diaryapp.screen.screens.ArticlesScreen
import com.example.diaryapp.screen.screens.BillScreen
import com.example.diaryapp.screen.screens.DiaryScreen
import com.example.diaryapp.screen.screens.DiarySheetScreen
import com.example.diaryapp.screen.screens.HomeScreen
import com.example.diaryapp.screen.screens.LetterScreen
import com.example.diaryapp.screen.screens.LoginScreen
import com.example.diaryapp.screen.screens.NotificationScreen
import com.example.diaryapp.screen.screens.PremiumScreen
import com.example.diaryapp.screen.screens.ProfileScreen
import com.example.diaryapp.screen.screens.SignUpScreen
import com.example.diaryapp.viewmodel.ArticlesViewModel
import com.example.diaryapp.viewmodel.AuthViewModel
import com.example.diaryapp.viewmodel.DiaryViewModel
import com.example.diaryapp.viewmodel.HomeViewModel
import com.example.diaryapp.viewmodel.LetterViewModel
import com.example.diaryapp.viewmodel.ZaloPayViewModel


@Composable
fun NavigationGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    context: Context,
    activity: Activity,
    authViewModel: AuthViewModel = viewModel(),
    diaryViewModel: DiaryViewModel = viewModel(),
    letterViewModel: LetterViewModel = viewModel(),
    zaloPayViewModel: ZaloPayViewModel = viewModel(),

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
                letterViewModel = letterViewModel,
                navController = navController,
                paddingValues = paddingValues
            )
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

        composable("${Screen.BillScreen.route}/{packageId}") {
            val packageId = it.arguments?.getString("packageId")
            if (packageId != null) {
                BillScreen(packageId, context = context, activity = activity, navController = navController, zaloPayViewModel = zaloPayViewModel)
            }
        }

        composable(Screen.PremiumScreen.route) {
            PremiumScreen(navController = navController, context = context, zaloPayViewModel)
        }

        composable(Screen.LetterScreen.route) {
            LetterScreen(letterViewModel = letterViewModel, navController = navController, context = context, )
        }

        composable(Screen.NotificationScreen.route) {
            //NotificationScreen( paddingValues = paddingValues, navController = navController )
            ArticlesScreen( navController = navController, paddingValues = paddingValues, context = context, articleViewModel = ArticlesViewModel(), )
        }

        composable(Screen.DiaryScreen.route) {
            DiaryScreen( diaryViewModel, navController, context, paddingValues )
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