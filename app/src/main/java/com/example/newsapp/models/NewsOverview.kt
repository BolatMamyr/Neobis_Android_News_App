package com.example.newsapp.models

data class NewsOverview(
    val status: String,
    val totalResults: Int?,
    val articles: List<Article>
)

data class Article(
    val source: NewsSource,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)

data class NewsSource(
    val id: String?,
    val name: String
)