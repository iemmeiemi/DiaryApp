package com.example.diaryapp.screen.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.sharp.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.diaryapp.data.Mood
import com.example.diaryapp.screen.navigation.Screen
import com.google.firebase.Timestamp


sealed class WidgetTag(
    val tag: String,
    val icon: ImageVector
) {
    object Diary: WidgetTag("diary", Icons.Filled.Create)
    object Goal: WidgetTag("goal", Icons.Outlined.Menu)
    object Letter: WidgetTag("letter", Icons.Sharp.MailOutline )

}

@Composable
fun SmallItemWidget(
    tag: String,
    title: String,
    previewContent: String,
    timestamp: Timestamp?,
    Id: String,
    navController: NavHostController,
) {
    val route = if (tag == "diary") {Screen.DiarySheetScreen.route}
    else if (tag == "letter") { Screen.DiarySheetScreen.route }
    else { }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp, 0.dp, 30.dp, 10.dp)
            .clickable {
                navController.navigate("${Screen.DiarySheetScreen.route}/${Id}")
            }
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.LightGray, shape = RoundedCornerShape(10.dp))
                .padding(10.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            var icon: ImageVector = WidgetTag.Diary.icon
            if (WidgetTag.Goal.tag == tag) {
                icon = WidgetTag.Goal.icon
            }
            if (WidgetTag.Letter.tag == tag) {
                icon = WidgetTag.Letter.icon
            }
            Icon(
                imageVector = icon,
                contentDescription = "Diary",
                modifier = Modifier
                    .background(
                        color = Color.Gray,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(5.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = previewContent,
                    maxLines = 1
                )
                Text(
                    text = timestamp?.toDate().toString(),
                    fontSize = 10.sp,
                    maxLines = 1
                )
            }
        }
    }

}

@Composable
fun MoodItem(mood: Mood) {
    Box(
        modifier = Modifier.clickable {

        }
    ) {
        AsyncImage(
            model = mood.icon,
            contentDescription = mood.description
        )
    }
}

@Composable
fun ImageModifiableBox(
    uri: Uri,
    content: String?,
    onDelete:() -> Unit
) {
    Box(
        modifier = Modifier
            .clickable { }
            .fillMaxSize()
    ) {
        AsyncImage(
            model = uri,
            contentDescription = content,
            Modifier
                .fillMaxSize(),
            contentScale  = ContentScale.Fit
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clickable {
                    onDelete()
                }
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Delete",
                tint = Color.White,
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.5f), shape = CircleShape)
                    .padding(8.dp)
                    .size(20.dp)
            )
        }
    }
}

@Preview
@Composable
fun preview() {
//    val navController = rememberNavController()
//    SmallItemWidget(tag = "diary", title = "Nice day", previewContent = "sdadsdfsfsdfsdfsdfsdfsdfsfsdfsdfsadadad12sdfsdfsdfsdfsdfsfsdfsdfsadadad1", "sffsdf", navController)
    //ImageModifiableBox()
}