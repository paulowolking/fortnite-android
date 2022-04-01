package com.wolking.fortnite.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wolking.fortnite.data.models.news.model.News
import com.wolking.fortnite.data.base.NetworkResult
import com.wolking.fortnite.domain.news.repository.NewsRepository
import com.wolking.fortnite.presentation.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    var news: MutableLiveData<Resource<NetworkResult<News>>> = MutableLiveData()

    fun getNews() = CoroutineScope(Dispatchers.Main).launch {
        news.value = withContext(Dispatchers.Default) {
            newsRepository.getNews()
        }
    }
}