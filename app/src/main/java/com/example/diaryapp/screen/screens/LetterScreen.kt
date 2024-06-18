package com.example.diaryapp.screen.screens

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.diaryapp.data.Letter
import com.example.diaryapp.data.Result
import com.example.diaryapp.data.TimeSchedule.Companion.timeScheduleList
import com.example.diaryapp.screen.components.CustomSpacerBlock
import com.example.diaryapp.screen.components.CustomeSpacerLine
import com.example.diaryapp.screen.components.ImageModifiableBox
import com.example.diaryapp.screen.components.ToastMaker
import com.example.diaryapp.screen.navigation.Screen
import com.example.diaryapp.theme.Background
import com.example.diaryapp.utils.getDate
import com.example.diaryapp.utils.timeAfter
import com.example.diaryapp.viewmodel.LetterViewModel
import com.google.firebase.Timestamp
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import java.time.LocalDate

@Composable
fun LetterScreen(
    letterViewModel: LetterViewModel,
    navController: NavHostController,
    context: Context,
    letterId: String = ""
) {
    var sendedAt: Timestamp? by remember { mutableStateOf(null) }
    var sendedAtString: String by remember { mutableStateOf(" ") }
    val result by letterViewModel.CRUDLetterResult.observeAsState()

    //get local image
    var uriList = remember { mutableStateListOf<Uri?>() }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                uriList += uri
            }
        }
    )

    var lt = ""
    var lc = ""
    var date = CalendarDay(
        position = DayPosition.MonthDate,
        date = LocalDate.now()
    ).date
    var letterTitle by remember { mutableStateOf(lt) }
    var letterContent by remember { mutableStateOf(lc) }
    val isNotNull = !letterId.isNullOrBlank()

    Column (
        modifier = Modifier.padding( 20.dp)
    ) {
        CustomSpacerBlock()
        Row {
            Text(
                text = date.toString(),
                modifier = Modifier
                    .background(color = Color.LightGray, shape = RoundedCornerShape(10.dp))
                    .padding(10.dp, 5.dp)
                    .fillMaxWidth()
                    .weight(1f)
            )
            CustomeSpacerLine()
            Text(
                text = sendedAtString,
                modifier = Modifier
                    .background(color = Color.LightGray, shape = RoundedCornerShape(10.dp))
                    .padding(10.dp, 5.dp)
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
            Spacer(modifier = Modifier.height(5.dp))




        CustomSpacerBlock()
        Row {
            OutlinedTextField(
                value = letterTitle,
                onValueChange = { letterTitle = it },
                label = { Text(text = "Title") },
                modifier = Modifier
                    .background(color = Background, shape = RoundedCornerShape(10.dp))
                    .padding(10.dp, 5.dp)
                    .fillMaxWidth()
                    .weight(1f)
            )
            CustomeSpacerLine()
            Column (
                modifier = Modifier
                    .padding(10.dp, 5.dp)
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(text = "Send me after...", fontSize = 17.sp )
                LazyRow(
                ) {
                    items(timeScheduleList) { time ->
                        Column(
                            modifier = Modifier
                                .background(color = Background, shape = RoundedCornerShape(20.dp))
                                .padding(10.dp)
                                .clickable {
                                    sendedAt = timeAfter(Timestamp.now(), time)
                                    sendedAtString = getDate(sendedAt).toString()
                                }
                        ) {
                            Text(text = time.title)
                        }
                        CustomeSpacerLine()
                    }
                }
            }
        }

        CustomSpacerBlock()
        OutlinedTextField(
            value = letterContent,
            onValueChange = { letterContent = it },
            label = { Text(text = "What do you want to send to your self?") },
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Background, shape = RoundedCornerShape(20.dp))
                .height(300.dp)
        )

        CustomSpacerBlock()
        //Hiển thị một nút để cho phép người dùng chọn ảnh
        Column(
            modifier = Modifier
                .wrapContentSize()
                .clickable {
                    launcher.launch("image/*")
                }
                .background(color = Background, shape = RoundedCornerShape(20.dp))
                .padding(16.dp),
            Arrangement.Center,
        ) {
            Text("Chọn ảnh")
        }
        CustomSpacerBlock()

        // Hiển thị ảnh đã chọn
        if (uriList.isNotEmpty()) {
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
                val d = Letter(id = letterId ,title = letterTitle, content = letterContent,  images = uriList.map {it.toString()}, false, sendedAt, getDate(Timestamp.now()))
                if (isNotNull) {
                    letterViewModel.updateLetter( d )
                } else {
                    letterViewModel.createLetter( d )
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
fun previewLetter() {
    //
    LetterScreen( LetterViewModel(), navController = rememberNavController(), context = LocalContext.current)
}