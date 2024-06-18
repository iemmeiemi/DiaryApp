package com.example.diaryapp.data

data class ArticleList(
    var status: String,
    var totalResults: Int,
    var articles: List<ArticleData?> = emptyList()
)
data class ArticleData(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
) {
    data class Source(
        val id: String?,
        val name: String
    )
}
