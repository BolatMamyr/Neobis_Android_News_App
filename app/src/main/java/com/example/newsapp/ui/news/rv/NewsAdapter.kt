package com.example.newsapp.ui.news.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemNewsBinding
import com.example.newsapp.models.Article

class NewsAdapter : PagingDataAdapter<Article, NewsAdapter.NewsViewHolder>(diffCallback) {

    private var news = mutableListOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.setData(getItem(position))

    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(article: Article?) {
            article?.let { article ->
                binding.tvTitleList.text = article.title
                binding.tvDateList.text = article.publishedAt
                binding.tvDescriptionList.text = article.description
                Glide.with(binding.root.context)
                    .load(article.urlToImage)
                    .placeholder(R.drawable.placeholder)
                    .into(binding.ivListItem)
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }
}