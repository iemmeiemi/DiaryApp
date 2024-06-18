package com.example.diaryapp.network

import com.example.diaryapp.data.ArticleData
import com.example.diaryapp.data.ArticleList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ArticlesApiService {
    @GET("everything")
    fun getArticles(
        @Query("q") query: String = "self-help",
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 5,
        @Query("apiKey") apiKey: String = "d531b0ef6fbb4ac4aad1ccb241f5498d"
    ): Call<ArticleList>
}


fun getArticles() {
    //everything?q=${keyword}&page=${page}&pageSize=${pageSize}&apiKey=d531b0ef6fbb4ac4aad1ccb241f5498d
    val keyword = "self-help"
    val page = "self-help"
    val pageSize = "10"

}