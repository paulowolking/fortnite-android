package com.wolking.fortnite.presentation.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wolking.fortnite.databinding.FragmentNewsBinding
import com.wolking.fortnite.presentation.Resource
import com.wolking.fortnite.presentation.viewmodels.NewsViewModel
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

    fun setupView() {
        with(binding.rvNews) {
            layoutManager = LinearLayoutManager(context)
            adapter = NewsAdapter(context)
        }
    }

    private fun registerObservers() {
        newsViewModel.getNews()

        newsViewModel.news.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.progress.isVisible = true
                }
                is Resource.Success -> {
                    binding.progressBar.progress.isVisible = false
                    it.data.data?.motds?.let { it1 ->
                        (binding.rvNews.adapter as NewsAdapter)
                            .updateItemsList(it1)
                    }
                }
                is Resource.Failure -> {
                    binding.progressBar.progress.isVisible = false
                    Log.e("Erro:", it.throwable.toString())
                }
            }
        })
    }
}