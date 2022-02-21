package com.wolking.fortnite.ui.news

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
import com.wolking.fortnite.R
import com.wolking.fortnite.presentation.Resource
import com.wolking.fortnite.presentation.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_shop.*
import kotlinx.android.synthetic.main.progress_bar.*

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_news, container, false)
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        registerObservers()
    }

    fun setupView() {
        with(rv_items) {
            layoutManager = LinearLayoutManager(context)
            adapter = NewsAdapter(context)
        }
    }

    private fun registerObservers() {
        newsViewModel.getNews()

        newsViewModel.news.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    progress.isVisible = true
                }
                is Resource.Success -> {
                    progress.isVisible = false
                    it.data.data?.motds?.let { it1 ->
                        (rv_items?.adapter as NewsAdapter)
                            .updateItemsList(it1)
                    }
                }
                is Resource.Failure -> {
                    progress.isVisible = false
                    Log.e("Erro:", it.throwable.toString())
                }
            }
        })
    }
}