package com.example.diaryapp.screen.components

import android.content.Context
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController

@Composable
fun CustomButton(
    icon: Unit,
    navController: NavHostController,
    context: Context = LocalContext.current,
    onClickFunction: () -> Unit,
) {
    Button(
        onClick = {
           onClickFunction
        }
    ) {
        icon
    }
}

