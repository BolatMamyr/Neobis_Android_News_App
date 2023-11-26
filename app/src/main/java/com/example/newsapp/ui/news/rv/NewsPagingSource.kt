package com.example.newsapp.ui.news.rv

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.models.Article
import com.example.newsapp.other.myLogE
import com.example.newsapp.repo.NewsRepository

class NewsPagingSource(
    private val newsRepository: NewsRepository
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val res = newsRepository.getNews(page = page.toString())
            val data = res.articles
            LoadResult.Page(
                data = res.articles,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            myLogE("NewsPagingSource: ${e.localizedMessage}")
            LoadResult.Error(e)
        }
    }
}