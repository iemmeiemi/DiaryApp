package com.example.diaryapp.screen.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.diaryapp.constants.NavItems
import com.example.diaryapp.screen.navigation.Screen
import com.example.diaryapp.theme.Background
import com.example.diaryapp.theme.Background2
import com.example.diaryapp.theme.Black

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavHostController
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Background,
            titleContentColor = Black
        ),
        title = {
            Text(
                "asd",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Profile"
                )
            }
        },
    )
}


@Composable
fun BottomBar(
    navController: NavHostController,
) {
    var selectedRoute by remember {
        mutableStateOf(Screen.HomeScreen.route)
    }
    NavigationBar(
        containerColor = Background,
        contentColor = Black
    ) {
        NavItems.bottomNavItems.forEach { navItem ->
            NavigationBarItem(
                selected = selectedRoute == navItem.route,
                onClick = {
                    selectedRoute = navItem.route
                    navController.navigate(navItem.route)
                },
                icon = {
                    if (selectedRoute == navItem.route) {
                        Icon(
                            imageVector = navItem.icon!!,
                            contentDescription = navItem.title,
                            tint = Black
                        )
                    } else {
                        Icon(
                            imageVector = navItem.icon!!,
                            contentDescription = navItem.title,
                            tint = Background2
                        )
                    }
                }
            )
        }
    }
}


@Preview
@Composable
fun previewBottomBar() {
    val navController = rememberNavController()
    BottomBar(navController = navController)
}
