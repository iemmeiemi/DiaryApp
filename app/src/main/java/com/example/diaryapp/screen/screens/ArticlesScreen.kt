package com.example.diaryapp.screen.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.diaryapp.data.ArticleData
import com.example.diaryapp.data.Diary
import com.example.diaryapp.screen.components.ArticleSmall
import com.example.diaryapp.screen.components.CustomSpacerBlock
import com.example.diaryapp.screen.components.CustomeSpacerLine
import com.example.diaryapp.viewmodel.ArticlesViewModel

@Composable
fun ArticlesScreen(
    navController: NavHostController,
    paddingValues: PaddingValues,
    context: Context = LocalContext.current,
    articleViewModel: ArticlesViewModel,
) {
    var page: Int by remember { mutableStateOf(1) }
    val articleList by articleViewModel.articles.observeAsState()
    articleViewModel.getArticles(page)
    //var displayArtical by remember { mutableStateOf(articleList) }
    if (articleList?.articles?.isEmpty() == false) {
        Log.e("erorooooo", "yesy")
    }
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 20.dp)
    ) {
        CustomSpacerBlock()
        Row {
            Button(onClick = { page++ }) {
                Text("Next")
            }
            CustomeSpacerLine()
            Button(onClick = { page-- }) {
                Text("Back")
            }
        }
        CustomSpacerBlock()
        LazyColumn (
        ) {

            Log.e("erorooooo", "content: "+ articleList?.articles?.size)

            articleList?.let {
                items(it.articles) { article ->
                    //ArticleSmall(ArticleData(ArticleData.Source("ssdfsf", "ddde"), "sdf", "asfad", "asd", "adasd", "https://www.cnet.com/a/img/resize/57c7db1e814332874240a006a4daa1eadf157e04/hub/2024/04/25/1ac5d8a3-1608-417e-9c3a-d550ba112ff5/img-6407.jpg?auto=webp&fit=crop&height=675&width=1200", "adwdw", "awdawd"), LocalContext.current)


                    if (article != null) {
                        //Log.e("erorooooo", "content: "+ article?.description.toString())
                        ArticleSmall(article, context)
                    }
                    CustomSpacerBlock()
                }
            }
        }

    }
}


@Preview
@Composable
fun previewArticles() {
    ArticlesScreen(navController = rememberNavController(), paddingValues = PaddingValues(20.dp), articleViewModel = ArticlesViewModel())
}