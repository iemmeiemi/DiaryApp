package com.example.diaryapp.screen.components

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.diaryapp.data.ArticleData
import com.example.diaryapp.data.Mood
import com.example.diaryapp.screen.navigation.Screen
import com.example.diaryapp.theme.Background
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

@Composable
fun ArticleSmall(
    articleData: ArticleData,
    context: Context
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(color = Background, shape = RoundedCornerShape(20.dp))
            .padding(20.dp)
            .clickable {
                articleData.url?.let {
                    val customTabsIntent = CustomTabsIntent.Builder().build()
                    customTabsIntent.launchUrl(context, Uri.parse(articleData.url))
                }

            }
    ) {
        articleData.urlToImage?.let {AsyncImage(model = articleData.urlToImage, contentDescription = null, modifier = Modifier.height(150.dp).background(color = Color.Transparent, shape = RoundedCornerShape(20.dp)))}
        CustomSpacerBlock()
        articleData.title?.let { Text(text = articleData.title, maxLines = 1, fontSize = 25.sp, fontWeight = FontWeight.Bold) }
        articleData.content?.let { Text(text = articleData.content, maxLines = 2, fontSize = 20.sp) }
        articleData.author?.let { Text(text = articleData.author, maxLines = 1, fontSize = 15.sp) }
    }
}


@Composable
fun ShopLabel(label: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(20.dp))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = label, fontSize = 20.sp)
        }
    }
}

@Composable
fun ShopItemWithOutImage(id: String, title: String, description: String, price: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(20.dp))
            .clickable {
                onClick()
            }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val id = id
            Text(text = "Price: " + price.toString(), fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
            Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold, minLines = 1)
            Text(text = description, fontSize = 14.sp, )
        }
    }
}


@Preview
@Composable
fun preview() {
//    val navController = rememberNavController()
//    SmallItemWidget(tag = "diary", title = "Nice day", previewContent = "sdadsdfsfsdfsdfsdfsdfsdfsfsdfsdfsadadad12sdfsdfsdfsdfsdfsfsdfsdfsadadad1", "sffsdf", navController)
    //ImageModifiableBox()
    ArticleSmall(ArticleData(ArticleData.Source("ssdfsf", "ddde"), "sdf", "asfad", "asd", "adasd", "https://www.cnet.com/a/img/resize/57c7db1e814332874240a006a4daa1eadf157e04/hub/2024/04/25/1ac5d8a3-1608-417e-9c3a-d550ba112ff5/img-6407.jpg?auto=webp&fit=crop&height=675&width=1200", "adwdw", "awdawd"), LocalContext.current)
}


