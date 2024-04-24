package com.example.diaryapp.screen.screens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.diaryapp.screen.navigation.Router
import com.example.diaryapp.screen.navigation.Screen
import com.example.diaryapp.viewmodel.HomeViewModel
import com.example.diaryapp.data.Result
import com.example.diaryapp.screen.components.SmallItemWidget

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    navController: NavHostController,
    paddingValues: PaddingValues = PaddingValues(),
    onNavigateToDiary: () -> Unit,
    onNavigateToPremium: () -> Unit,
    finish: () -> Unit
) {
    BackHandler {
        finish()
    }

    val diaries by homeViewModel.diaries.observeAsState(emptyList())
    homeViewModel.getDiaries()

    Column (
    ) {
        homeViewModel.getDiaries()
        Log.i("info", diaries.isEmpty().toString())
        if(!diaries.isEmpty()) {
            LazyColumn(
                modifier = Modifier.weight(1f)

            ) {
                items(diaries) {diary ->
                    SmallItemWidget(
                        tag = "diary",
                        title = diary.title,
                        previewContent = diary.content,
                        diaryId = diary.id,
                        onNavigateToSpecificDiary = { navController.navigate(Screen.DiarySheetScreen.route) }
                    )
                }
            }
        }
        Button(
            onClick = {
                onNavigateToDiary()
        }) {
            Text(text = "Diary")
        }
        Button(
            onClick = {
                onNavigateToPremium()
            }) {
            Text(text = "Premium")
        }

    }
}

//@Preview
//@Composable
//fun preview() {
//    HomeScreen() {
//
//    }
//}

@Composable
fun WidgetList(widget: List<*>) {
    Column {
        widget.forEach { widget ->

        }
    }
}