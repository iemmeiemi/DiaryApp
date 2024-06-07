package com.example.diaryapp.screen.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.diaryapp.screen.components.CustomButton2
import com.example.diaryapp.screen.components.CustomSpacerBlock
import com.example.diaryapp.screen.components.CustomeSpacerLine
import com.example.diaryapp.screen.components.SmallItemWidget
import com.example.diaryapp.screen.components.ToastMaker
import com.example.diaryapp.screen.navigation.Screen
import com.example.diaryapp.viewmodel.DiaryViewModel

@Composable
fun AllDiariesScreen(
    navController: NavHostController,
    paddingValues: PaddingValues,
    context: Context = LocalContext.current,
    diaryViewModel: DiaryViewModel,
) {
    val diaries by diaryViewModel.diaries.observeAsState(emptyList())
    var hasDiaries by remember { mutableStateOf(false) }

    // Observe sự thay đổi của danh sách diaries và cập nhật hasDiaries
    LaunchedEffect(diaries) {
        hasDiaries = diaries.isNotEmpty()
    }
    var typeOfOrder by remember { mutableStateOf("From last") }

    Column (
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row {
            CustomButton2(modifier = Modifier.width(150.dp), text = "From first") {
                typeOfOrder = "from first"
            }
            CustomeSpacerLine()
            CustomButton2(modifier = Modifier.width(150.dp), text = "From last") {
                typeOfOrder = "from last"
            }
        }
        CustomSpacerBlock()
        if (hasDiaries) {
            var displayList by remember { mutableStateOf(diaries)}
            LazyColumn(
                modifier = Modifier
                    .height(600.dp)
            ) {
                if (typeOfOrder == "from last") {
                    displayList = diaries
                } else {
                    displayList = diaries.reversed()
                }
                items(displayList) { diary ->
                    SmallItemWidget(
                        tag = "diary",
                        title = diary.title,
                        previewContent = diary.content,
                        timestamp = diary.createdAt,
                        Id = diary.id,
                        navController = navController,
                    )
                }
            }
        } else {
            ToastMaker(context, "You've gotten no diary, Let's make one!")
            CustomButton2(modifier = Modifier.width(200.dp), text = "Diary") {
                navController.navigate(Screen.DiaryScreen.route) {
                    launchSingleTop = true
                }
            }
        }
    }
}

@Preview
@Composable
fun previewedAllDiariesScreen() {
    AllDiariesScreen(navController = rememberNavController(), paddingValues = PaddingValues(20.dp), diaryViewModel = DiaryViewModel())
}