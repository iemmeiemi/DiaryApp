package com.example.diaryapp.screen.screens

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.diaryapp.data.Diary
import com.example.diaryapp.data.Result
import com.example.diaryapp.screen.components.CustomSpacerBlock
import com.example.diaryapp.screen.components.ImageModifiableBox
import com.example.diaryapp.screen.components.ToastMaker
import com.example.diaryapp.screen.navigation.Screen
import com.example.diaryapp.utils.getDate
import com.example.diaryapp.viewmodel.DiaryViewModel
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import java.time.LocalDate

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
    var date = CalendarDay(
        position = DayPosition.MonthDate,
        date = LocalDate.now()
    ).date.toString()
    val isNotNull = !diaryId.isNullOrBlank()
    if (isNotNull) {
        val diary = diaries?.find { d -> d.id == diaryId }
        if (diary != null) {
            dt = diary.title
            dc = diary.content
            date = getDate(diary.createdAt).toString()
        }
    }
    var diaryTitle by remember { mutableStateOf(dt) }
    var diaryContent by remember { mutableStateOf(dc) }

    var uriList = remember { mutableStateListOf<Uri?>() }
    //var storageUriList: List<Uri> by remember { mutableStateOf(emptyList()) }
    //var imageUri: Uri? by remember { mutableStateOf(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                uriList += uri
            }
        }
    )

    Column (
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 20.dp)
    ) {
        CustomSpacerBlock()

        Row {
            Column {
                Text(
                    text = date,
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


        // Hiển thị một nút để cho phép người dùng chọn ảnh
        Row {
            Button(onClick = {
                launcher.launch("image/*")
            }) {
                Text("Chọn ảnh")
            }
        }
        CustomSpacerBlock()

        // Hiển thị ảnh đã chọn
        if(uriList.isNotEmpty()) {
            LazyRow(
                modifier = Modifier.height(150.dp)
            ) {
                items(uriList.size) { index ->
                    uriList[index]?.let {
                        ImageModifiableBox(it, null) {
                            uriList.removeAt(index)
                        }
                    }
                    CustomSpacerBlock()
                }
            }
        }
        CustomSpacerBlock()
        Button(
            onClick = {
                val d = Diary(id = diaryId ,title = diaryTitle, content = diaryContent,  images = uriList.map {it.toString()})
                if (isNotNull) {
                    diaryViewModel.updateDiary( d )
                } else {
                    diaryViewModel.createDiary( d )
                }
                if(uriList.isNotEmpty()) {

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



@Preview
@Composable
fun previewDiary() {
    DiaryScreen( DiaryViewModel(), rememberNavController(), LocalContext.current, PaddingValues(20.dp))
}
