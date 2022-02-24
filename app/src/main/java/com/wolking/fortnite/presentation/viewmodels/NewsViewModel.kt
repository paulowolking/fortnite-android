package com.wolking.fortnite.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import com.wolking.fortnite.data.models.news.model.News
import com.wolking.fortnite.data.base.Response
import com.wolking.fortnite.domain.news.repository.NewsRepository
import com.wolking.fortnite.presentation.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : DisposingViewModel() {

    var news = MutableLiveData<Resource<Response<News>>>()

    fun getNews(refresh: Boolean = false) {

        news.value = Resource.Loading()

        addDisposable(newsRepository.getNews().subscribe(
            { data ->
                news.value = Resource.Success(data)
            },
            { error ->
                news.value = Resource.Failure(error)
            }
        ))

    }
}