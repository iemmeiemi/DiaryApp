package com.example.diaryapp.screen.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.diaryapp.screen.components.BottomBar
import com.example.diaryapp.screen.components.TopBar
import com.example.diaryapp.screen.navigation.NavigationGraph
import com.example.diaryapp.screen.navigation.Router
import com.example.diaryapp.screen.navigation.RouterImpl
import com.example.diaryapp.screen.navigation.Screen
import com.example.diaryapp.screen.navigation.navControllerContainer
import com.example.diaryapp.viewmodel.AuthViewModel
import com.example.diaryapp.viewmodel.DiaryViewModel
import com.example.diaryapp.viewmodel.HomeViewModel


//

//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val route = navBackStackEntry?.destination?.route ?: Screen.SignupScreen.route
//    val router: Router = remember { RouterImpl(navController, route) }
//    val isFullScreen = Screen.isFullScreen(route)
@Composable
fun MainLayout( context: Context) {

    val navController = rememberNavController()

    //tác vụ tốn nhiều thời gian
    var currentFrame =  navController.currentBackStackEntryAsState().value?.destination?.route.toString()
    val applyLayout = (currentFrame !== Screen.SignupScreen.route && currentFrame !== Screen.LoginScreen.route)
    Log.e("route", currentFrame )

    Scaffold(
        topBar = {
            if ( applyLayout ) {
                TopBar(navController = navController)
            }
        },

        bottomBar = {
            if ( applyLayout ) {
                BottomBar(navController = navController)
            }
        },

        floatingActionButton = {

        },

    ) {
        NavigationGraph(navController = navController, it, context)//
    }
}