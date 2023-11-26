package com.example.newsapp.ui.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.ui.news.rv.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<NewsViewModel>()

    private val mAdapter = NewsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivFavorites.setOnClickListener {
            findNavController().navigate(R.id.action_newsFragment_to_savedFragment)
        }

        binding.rvNews.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.newsPaging.collect {
                mAdapter.submitData(it)
            }

            mAdapter.loadStateFlow.collectLatest {
                when (it.refresh) {
                    is LoadState.Loading -> {
                        showProgressBar(true)
                    }
                    is LoadState.NotLoading -> {
                        showProgressBar(false)
                    }
                    is LoadState.Error -> {
                        // TODO: show error
                    }
                }
            }
        }
    }

    private fun showProgressBar(show: Boolean) {
        binding.pbNews.isVisible = show
        binding.rvNews.isVisible = !show
    }

}