package com.example.diaryapp.screen.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.diaryapp.data.Diary
import com.example.diaryapp.data.Result
import com.example.diaryapp.screen.navigation.Screen
import com.example.diaryapp.viewmodel.DiaryViewModel

@Composable
fun DiaryScreen(
    diaryViewModel: DiaryViewModel,
    navController: NavHostController,
    context: Context,
    paddingValues: PaddingValues,
    diaryId : String = "",
) {
    val result by diaryViewModel.CRUDDiaryResult.observeAsState()
    val diaries by diaryViewModel.diaries.observeAsState()
    var dt = ""
    var dc = ""
    val isNotNull = !diaryId.isNullOrBlank()
    if (isNotNull) {
        val diary = diaries?.find { d -> d.id == diaryId }
        if (diary != null) {
            dt = diary.title
            dc = diary.content
        }
    }
    var diaryTitle by remember { mutableStateOf(dt) }
    var diaryContent by remember { mutableStateOf(dc) }



    Column (modifier = Modifier.padding(paddingValues)) {
        Row {
            Column {
                Text(
                    text = "dd/MM/yyyy",
                    modifier = Modifier
                        .background(color = Color.LightGray, shape = RoundedCornerShape(10.dp))
                        .padding(10.dp, 5.dp)
                        .width(150.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))

                OutlinedTextField(
                    value = diaryTitle,
                    onValueChange = { diaryTitle = it },
                    label = { Text(text = "Title") },
                    modifier = Modifier
                        .background(color = Color.LightGray, shape = RoundedCornerShape(10.dp))
                        .padding(10.dp, 5.dp)
                        .width(150.dp)
                )
            }
            Spacer(modifier = Modifier.width(20.dp))

            Card(
                modifier = Modifier
                    .background(color = Color.LightGray, shape = RoundedCornerShape(10.dp))
                    .padding(10.dp)
            ) {
                Text(text = "Diary Streak",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center

                )
                Text(text = "5",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
                Text(text = "Keep going!",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = diaryContent,
            onValueChange = { diaryContent = it },
            label = { Text(text = "How you doing?")},
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.LightGray)
                .height(300.dp)
        )

        Button(
            onClick = {
                val d = Diary(id = diaryId ,title = diaryTitle, content = diaryContent)
                if (isNotNull) {
                    diaryViewModel.updateDiary( d )
                } else {
                    diaryViewModel.createDiary( d )
                }

                when(result) {
                    is Result.Success -> {
                        ToastMaker(context = context, "Successfully!")
                        navController.navigate(Screen.HomeScreen.route) {
                            popUpTo(Screen.DiaryScreen.route) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                    is Result.Error -> {
                        ToastMaker(context = context, (result as Result.Error).exception.message.toString())
                    }
                    else -> {

                    }

                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Done")
        }
    }
}

fun ToastMaker(context: Context, text: String) {
    Toast.makeText(
        context,
        text,
        Toast.LENGTH_SHORT
    ).show()
}

