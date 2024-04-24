package com.example.diaryapp.screen.screens

import android.content.Context
import android.util.Log
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.diaryapp.screen.navigation.NavigationGraph
import com.example.diaryapp.screen.navigation.Router
import com.example.diaryapp.screen.navigation.RouterImpl
import com.example.diaryapp.screen.navigation.Screen
import com.example.diaryapp.screen.navigation.navControllerContainer
import com.example.diaryapp.viewmodel.AuthViewModel
import com.example.diaryapp.viewmodel.DiaryViewModel
import com.example.diaryapp.viewmodel.HomeViewModel

@Composable
fun MainLayout(finish: () -> Unit, context: Context) {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
    val homeViewModel: HomeViewModel = viewModel()
    val diaryViewModel: DiaryViewModel = viewModel()
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val route = navBackStackEntry?.destination?.route ?: Screen.SignupScreen.route
//    val router: Router = remember { RouterImpl(navController, route) }
//    val isFullScreen = Screen.isFullScreen(route)

    Scaffold(

        bottomBar = {
            var selectedItem by remember { mutableIntStateOf(0) }
            val items = listOf("Home", "Profile", "Notification", "Setting")
            val icons = listOf<ImageVector>( Icons.Filled.Home, Icons.Filled.AccountCircle, Icons.Filled.Notifications, Icons.Filled.Settings)
            val currentFrame = navController.currentBackStackEntryAsState().value?.destination?.label

            if ( !(currentFrame === Screen.SignupScreen.route) || !(currentFrame === Screen.LoginScreen.route) ) {
                NavigationBar() {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = { Icon(icons[index], contentDescription = null) },
                            label = { Text(item) },
                            selected = selectedItem == index,
                            onClick = { selectedItem = index }
                        )
                    }
                }
            }

        }
    ) {
          NavigationGraph(navController = navController, authViewModel, homeViewModel, diaryViewModel, it, context, finish)
    }


}