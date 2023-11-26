package com.example.newsapp.ui.news

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.newsapp.repo.NewsRepository
import com.example.newsapp.ui.news.rv.NewsPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    val newsPaging = Pager(PagingConfig(pageSize = 100)) {
        NewsPagingSource(newsRepository)
    }.flow
}