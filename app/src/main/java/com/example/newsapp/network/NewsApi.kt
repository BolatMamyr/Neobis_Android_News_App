package com.example.newsapp.network

import com.example.newsapp.models.NewsOverview
import com.example.newsapp.other.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getLatestNews(
        @Query("q")
        query: String = "android",
        @Query("sortBy")
        sortBy: String = "popularity",
        @Query("page")
        page: String = "1",
        @Query("apiKey")
        apiKey: String = API_KEY
    ): NewsOverview
}