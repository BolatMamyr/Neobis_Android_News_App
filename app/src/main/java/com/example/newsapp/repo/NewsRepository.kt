package com.example.newsapp.repo

import com.example.newsapp.models.NewsOverview

interface NewsRepository {
    suspend fun getNews(page: String): NewsOverview
}