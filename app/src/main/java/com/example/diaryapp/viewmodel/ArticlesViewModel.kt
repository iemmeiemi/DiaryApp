package com.example.diaryapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diaryapp.data.ArticleData
import com.example.diaryapp.data.ArticleList
import com.example.diaryapp.data.Diary
import com.example.diaryapp.data.Result
import com.example.diaryapp.data.repositories.DiaryRepository
import com.example.diaryapp.di.FireBaseInjection
import com.example.diaryapp.network.NewsApiServices
import kotlinx.coroutines.launch

class ArticlesViewModel: ViewModel() {
    private val _articles = MutableLiveData<ArticleList>()
    val articles: LiveData<ArticleList> get() = _articles
    private val newsApiServices: NewsApiServices
    init {
        newsApiServices = NewsApiServices()
    }

    fun getArticles(page: Int) {
        viewModelScope.launch {

            val result = newsApiServices.getArticles2(page)


            when (result) {

                is Result.Success -> {
                    _articles.value = result.data as ArticleList
                    //Log.e("erorooooo", "hẻe: "+ _articles.value )
                }

                is Error -> {
                }
                else -> {}
            }
        }
    }
}