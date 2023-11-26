package com.example.newsapp.di

import com.example.newsapp.network.NewsApi
import com.example.newsapp.other.Constants.BASE_URL
import com.example.newsapp.repo.NewsRepository
import com.example.newsapp.repo.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Modules {

    @Provides
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)

    @Provides
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository = NewsRepositoryImpl(newsApi)
}