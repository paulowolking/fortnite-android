package com.wolking.fortnite.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wolking.fortnite.data.AppRepository
import com.wolking.fortnite.data.model.News
import com.wolking.fortnite.data.model.Response
import com.wolking.fortnite.data.model.Shop
import com.wolking.fortnite.presentation.Resource

class NewsViewModel(
    private val appRepository: AppRepository
) : DisposingViewModel() {

    var news = MutableLiveData<Resource<Response<News>>>()

    fun getNews(refresh: Boolean = false) {

        news.value = Resource.Loading()

        addDisposable(appRepository.getNews().subscribe(
            { data ->
                news.value = Resource.Success(data)
            },
            { error ->
                news.value = Resource.Failure(error)
            }
        ))

    }
}