package com.example.diaryapp.screen.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.diaryapp.data.Diary
import com.example.diaryapp.screen.components.CalendarComponent
import com.example.diaryapp.screen.components.CenterTextField
import com.example.diaryapp.screen.components.CustomButton2
import com.example.diaryapp.screen.components.SmallItemWidget
import com.example.diaryapp.screen.navigation.Screen
import com.example.diaryapp.theme.Background
import com.example.diaryapp.theme.Background2
import com.example.diaryapp.theme.Black
import com.example.diaryapp.utils.getDate
import com.example.diaryapp.viewmodel.AuthViewModel
import com.example.diaryapp.viewmodel.DiaryViewModel
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import java.time.LocalDate

@Composable
fun HomeScreen(
    diaryViewModel: DiaryViewModel,
    authViewModel: AuthViewModel,
    navController: NavHostController,
    context: Context = LocalContext.current,
    paddingValues: PaddingValues,
) {
    val diaries by diaryViewModel.diaries.observeAsState(emptyList())
    diaryViewModel.getDiaries()
   // authViewModel.getUserInfo()
    //    val userInfo by authViewModel.userInfo.observeAsState()

    //val diaryInOneDay by diaryViewModel.diaryInOneDay.observeAsState()
    val selections = remember {
        mutableStateListOf(
            CalendarDay(
                position = DayPosition.MonthDate,
                date = LocalDate.now()
            )
        )
    }

    val type = remember { mutableStateOf("day") }

    Column (
        modifier = Modifier
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
    ) {
        //Dashboard
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Background2)
                .padding(20.dp, 10.dp)
        ) {
            Row(
                modifier = Modifier
                    .background(Background, shape = RoundedCornerShape(20.dp))
                    .padding(10.dp),
            ) {
                Text(
                    text = "HI ", // + userInfo?.lastName + " " + userInfo?.firstName
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(150.dp, 100.dp)
                        .background(Background, shape = RoundedCornerShape(20.dp))
                        .padding(20.dp, 10.dp)
                ) {
                    Column {

                        Text(
                            text = "Diary Streak",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "5",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 40.sp
                        )
                        Text(
                            text = "Keep Going!",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier
                        .size(150.dp, 100.dp)
                        .background(Background, shape = RoundedCornerShape(20.dp))
                ) {
                    Text(text = "Chart")
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            modifier = Modifier.width(100.dp),
            onClick = {
                navController.navigate(Screen.AllDiariesScreen.route) {
                    launchSingleTop = true
                }
            },
        ) {
            Text(text = "All")
        }
        Spacer(modifier = Modifier.width(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            CalendarComponent(selections = selections, type = type)
            Spacer(modifier = Modifier.height(10.dp))

            var displayDiaries by remember { mutableStateOf(emptyList<Diary>()) }
            if (type.value == "day") {
                Log.e("date", selections[0].date.toString())
                //CalendarDay(date=2024-05-06, position=MonthDate)
                displayDiaries = diaries.filter { diary ->
                    getDate(diary.createdAt).toString() == selections[0].date.toString()
                }
            }

            if (displayDiaries.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        //.weight(1f)
                        .height(200.dp)

                ) {
                    items(displayDiaries) { diary ->
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
                CenterTextField(text = "There's no widget this day...")
//                LazyRow() {
//                    items(Mood.moodArray) { mood ->
//                        MoodItem(mood)
//                    }
//                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                Arrangement.Center
            ) {
                CustomButton2(modifier = Modifier.width(120.dp), "Diary") {
                    navController.navigate(Screen.DiaryScreen.route)
                }
                Spacer(modifier = Modifier.width(20.dp))

            }
        }
    }
}



@Preview
@Composable
fun PreviewedHomeScreen() {
    HomeScreen(DiaryViewModel(), AuthViewModel(), rememberNavController(), paddingValues = PaddingValues(20.dp))
}