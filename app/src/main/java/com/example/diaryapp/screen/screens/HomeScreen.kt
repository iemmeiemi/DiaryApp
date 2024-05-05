package com.example.diaryapp.screen.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diaryapp.screen.components.CalendarComponent
import com.example.diaryapp.theme.Background
import com.example.diaryapp.theme.Background2
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import java.time.LocalDate

@Composable
fun HomeScreen(
//    diaryViewModel: DiaryViewModel,
//    navController: NavHostController,
   // paddingValues: PaddingValues,
) {

    val selections = remember { mutableStateListOf<CalendarDay>( CalendarDay(position = DayPosition.MonthDate, date = LocalDate.now() ) ) }
    val type = remember { mutableStateOf<String>("month") }


    Column {
        //Dashboard
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Background2)
                .padding(20.dp, 10.dp)
        ) {
            Row (
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
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box (
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

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            CalendarComponent(selections = selections, type = type)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row {

        }
    }



//    BackHandler {
//        finish()
//    }

    /*
    val diaries by diaryViewModel.diaries.observeAsState(emptyList())


    Column (
        modifier = Modifier
            .padding(paddingValues)
//            .verticalScroll(rememberScrollState())
    ) {
        diaryViewModel.getDiaries()
        Log.i("info", diaries.isEmpty().toString())
        Column (
            modifier = Modifier
                .height(400.dp)
//                .verticalScroll(rememberScrollState())
        ) {
            if(!diaries.isEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .height(200.dp)

                ) {
                    items(diaries) {diary ->
                        SmallItemWidget(
                            tag = "diary",
                            title = diary.title,
                            previewContent = diary.content,
                            Id = diary.id,
                            navController = navController,
                        )
                    }
                }
            }
        }


        Row {
            Button(
                onClick = {
                    navController.navigate(Screen.DiaryScreen.route)
                }) {
                Text(text = "Diary")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                onClick = {
                    navController.navigate(Screen.PremiumScreen.route)
                }) {
                Text(text = "Premium")
            }
        }

    }*/
}

@Preview
@Composable
fun PreviewHomeScreen() {
//    val diaryViewModel: DiaryViewModel = viewModel()
//    val navController = rememberNavController()
    HomeScreen()
}
//
//@Composable
//fun WidgetList(widget: List<*>) {
//    Column {
//        widget.forEach { widget ->
//
//        }
//    }
//}