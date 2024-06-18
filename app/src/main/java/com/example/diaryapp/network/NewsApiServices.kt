package com.example.diaryapp.network

import android.util.Log
import com.example.diaryapp.data.ArticleData
import com.example.diaryapp.data.ArticleList
import com.example.diaryapp.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NewsApiServices {

    private val BASE_URL = "https://newsapi.org/v2/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(ArticlesApiService::class.java)

    suspend fun getAllArticles(page: Int) : Result<ArticleList?>? =
        try {
            val call = retrofit.getArticles(page = page)
            var result: ArticleList? = null
            Log.e("erorooooo", "1")
            call.enqueue(object : Callback<ArticleList> {
                override fun onResponse(
                    call: Call<ArticleList>,
                    response: Response<ArticleList>
                ) {
                    Log.e("erorooooo", "2")
                    // Xử lý dữ liệu từ response
                    if (response.isSuccessful && response.body() != null) {
                        Log.e("erorooooo", "3")
                        //response.body()?.articles?.let {
//                        for (i in it) {
//                            Log.e("erorooooo", i.toString())
//                        }
                        //}
                        result = response.body()
                        //Log.e("erorooooo", result.toString())
                    } else {
                        Log.e("erorooooo", "Error fetching articles")
                        Result.Error(Exception("Error fetching articles"))
                    }
                }

                override fun onFailure(call: Call<ArticleList>, t: Throwable) {
                    Log.e("erorooooo", "error: ${t.message}")
                    // Xử lý lỗi
                    Result.Error(Exception("Error fetching articles"))
                }
            })
            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }

     fun getArticles(page: Int) : Result<ArticleList?>? {
        val call = retrofit.getArticles(page = page)
        var result: Result<Nothing>? = null
        Log.e("erorooooo", "1")
        call.enqueue(object : Callback<ArticleList> {
            override fun onResponse(
                call: Call<ArticleList>,
                response: Response<ArticleList>
            ) {
                Log.e("erorooooo", "2")
                // Xử lý dữ liệu từ response
                if (response.isSuccessful && response.body() != null) {
                    Log.e("erorooooo", "3")
                    //response.body()?.articles?.let {
//                        for (i in it) {
//                            Log.e("erorooooo", i.toString())
//                        }
                    //}
                    result = Result.Success(response.body())
                    Log.e("erorooooo", "null: " + result.toString())
                } else {
                    Log.e("erorooooo", "Error fetching articles")
                    result = Result.Error(Exception("Error fetching articles"))
                }
            }

            override fun onFailure(call: Call<ArticleList>, t: Throwable) {
                Log.e("erorooooo", "error: ${t.message}")
                // Xử lý lỗi
                result = Result.Error(Exception("Error fetching articles"))
            }
        })
        return result

    }

    suspend fun getArticles2(page: Int): Result<ArticleList?> {
        return withContext(Dispatchers.IO) {
            val call = retrofit.getArticles(page = page)
            try {
                val response = call.execute()
                if (response.isSuccessful && response.body() != null) {
                    Result.Success(response.body())
                } else {
                    Result.Error(Exception("Error fetching articles"))
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }
//    object MarsApi {
//        val retrofitService : ArticlesApiService by lazy {
//            retrofit.create(ArticlesApiService::class.java) }
//    }
}