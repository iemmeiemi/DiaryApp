package com.example.diaryapp.data

import androidx.compose.ui.graphics.vector.ImageVector


data class NavItem(
    val title: String,
    val icon: ImageVector? = null,
    val route: String
)
