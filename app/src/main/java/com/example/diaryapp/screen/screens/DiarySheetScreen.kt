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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.diaryapp.data.Diary

@Composable
fun DiarySheetScreen(
    diary: Diary?,
    onNavigateToDiaryForUpdate: (diary: Diary) -> Unit
) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
    ){
        Column {
            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                var offset by remember { mutableStateOf(0f) }
                Text(
                    text = diary?.title ?: "",
                    modifier = Modifier
                        .background(color = Color.LightGray, shape = RoundedCornerShape(10.dp))
                        .padding(10.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = diary?.title ?: "",
                    modifier = Modifier
                        .background(color = Color.LightGray, shape = RoundedCornerShape(10.dp))
                        .padding(10.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Box (
                    modifier = Modifier
                        .background(color = Color.LightGray, shape = RoundedCornerShape(10.dp))
                        .padding(10.dp)
                        .fillMaxWidth()
                        .height(200.dp)
//                        .scrollable(
//                            state = rememberScrollableState { delta ->
//                                offset = offset + delta
//                                delta
//                            },
//                            orientation = Orientation.Vertical,
//                        )
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(text = diary?.content ?: "Nothing Here...")
                }
            }

            Row {
                Button(
                    onClick = {
                        if (diary != null) {
                            onNavigateToDiaryForUpdate( diary)
                        } else {

                        }
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Edit, contentDescription = "edit")
                }

                Spacer(modifier = Modifier.width(20.dp))
                Button(
                    onClick = {

                    }
                ) {
                    Icon(imageVector = Icons.Filled.Clear, contentDescription = "delete")
                }
            }
        }

    }

}
//
//@Preview
//@Composable
//fun previewSheet() {
//    val content = "When navigating using an action, you can optionally pop additional destinations off of the back stack. For example, if your app has an initial login flow, once a user has logged in, you should pop all of the login-related destinations off of the back stack so that the Back button doesn't take users back into the login flow.o remove destinations from the back stack when navigating from one destination to another, add a popUpTo() argument to the associated navigate() function call. popUpTo() instructs the Navigation library to remove some destinations from the back stack as part of the call to navigate(). The parameter value is the identifier of a destination on the back stack. The identifier can be an integer id or string route."
//    DiarySheetScreen(diary = Diary("sdasd", "title", content))
//}