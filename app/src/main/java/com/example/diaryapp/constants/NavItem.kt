package com.example.diaryapp.constants

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import com.example.diaryapp.data.NavItem
import com.example.diaryapp.screen.navigation.Screen


object NavItems {
    val navItems = listOf(
        NavItem(
            title = "Create Product",
            icon = Icons.Outlined.Add,
            route = "create_product"
        )
    )

    val bottomNavItems = listOf(
        NavItem(
            title = "Home",
            icon = Icons.Outlined.Home,
            route = Screen.HomeScreen.route
        ),
        NavItem(
            title = "Profile",
            icon = Icons.Outlined.Person,
            route = Screen.ProfileScreen.route
        ),
        NavItem(
            title = "Notification",
            icon = Icons.Outlined.Notifications,
            route = Screen.NotificationScreen.route
        ),
        NavItem(
            title = "Setting",
            icon = Icons.Outlined.Settings,
            route = Screen.SettingScreen.route
        ),
    )
    val topNavItems = listOf(
        NavItem(
            title = "Search",
            icon = Icons.Outlined.Search,
            route = "search"
        )
    )
}