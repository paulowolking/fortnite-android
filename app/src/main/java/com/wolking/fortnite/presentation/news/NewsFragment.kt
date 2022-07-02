package com.wolking.fortnite.presentation.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.wolking.fortnite.databinding.FragmentNewsBinding
import com.wolking.fortnite.presentation.news.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private val newsViewModel: NewsViewModel by viewModels()
    private lateinit var binding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        registerObservers()
    }

    private fun setupView() {
        with(binding.rvNews) {
            layoutManager = LinearLayoutManager(context)
            adapter = NewsAdapter(context)
        }
    }

    private fun registerObservers() {
        newsViewModel.getNews()
        newsViewModel.news.observe(viewLifecycleOwner) {
            (binding.rvNews.adapter as NewsAdapter)
                .updateItemsList(it)
        }

        newsViewModel.loading.observe(viewLifecycleOwner) {
            binding.progressBar.progress.isVisible = it
        }
    }
}