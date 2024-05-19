package com.example.diaryapp.screen.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.diaryapp.screen.components.CalendarComponent
import com.example.diaryapp.screen.components.CenterTextField
import com.example.diaryapp.screen.components.CustomButton2
import com.example.diaryapp.screen.components.SmallItemWidget
import com.example.diaryapp.screen.navigation.Screen
import com.example.diaryapp.theme.Background
import com.example.diaryapp.theme.Background2
import com.example.diaryapp.viewmodel.DiaryViewModel
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import java.time.LocalDate

@Composable
fun HomeScreen(
    diaryViewModel: DiaryViewModel,
    navController: NavHostController,
    context: Context = LocalContext.current,
    paddingValues: PaddingValues,
) {
    val diaries by diaryViewModel.diaries.observeAsState(emptyList())
    diaryViewModel.getDiaries()
    //Log.i("info", diaries.isEmpty().toString())
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
        modifier = Modifier.padding(paddingValues)
            .padding(horizontal = 30.dp)
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
                    text = "HI Username",
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            CalendarComponent(selections = selections, type = type)
            Spacer(modifier = Modifier.height(10.dp))

//            if (type.value == "day") {
//                Log.e("date", selections[0].date.toString())
//                //CalendarDay(date=2024-05-06, position=MonthDate)
//                diaryViewModel.getDiariesInOneDay(selections[0].date.toString())
//                diaryViewModel.display("one")
//            }

            if (diaries.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .height(200.dp)

                ) {
                    items(diaries) { diary ->
                        SmallItemWidget(
                            tag = "diary",
                            title = diary.title,
                            previewContent = diary.content,
                            Id = diary.id,
                            navController = navController,
                        )
                    }
                }
            } else {
                CenterTextField(text = "There's no widget this day... Let's make one!")
                LazyRow() {
                    item() {  }
                }
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


//
//@Preview
//@Composable
//fun PreviewedHomeScreen() {
//    HomeScreen()
//}