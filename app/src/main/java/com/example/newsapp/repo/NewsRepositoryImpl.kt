package com.example.newsapp.repo

import com.example.newsapp.models.NewsOverview
import com.example.newsapp.network.NewsApi
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {
    override suspend fun getNews(page: String): NewsOverview {
        return newsApi.getLatestNews(page = page)
    }
}