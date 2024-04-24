package com.example.diaryapp.screen.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.sharp.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diaryapp.R
import com.google.firebase.annotations.concurrent.Background

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
    diaryId: String,
    onNavigateToSpecificDiary: (diaryId: String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp, 0.dp, 30.dp, 10.dp)
            .clickable {
                onNavigateToSpecificDiary(diaryId)
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
                modifier = Modifier.background(
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
            }
        }
    }

}

@Preview
@Composable
fun preview() {
    SmallItemWidget(tag = "diary", title = "Nice day", previewContent = "sdadsdfsfsdfsdfsdfsdfsdfsfsdfsdfsadadad12sdfsdfsdfsdfsdfsfsdfsdfsadadad1", "áddad") {

    }
}