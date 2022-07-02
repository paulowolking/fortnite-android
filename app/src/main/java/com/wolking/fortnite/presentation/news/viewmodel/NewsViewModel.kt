package com.wolking.fortnite.presentation.news.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.wolking.fortnite.domain.news.repository.NewsRepository
import com.wolking.fortnite.data.core.Resource
import com.wolking.fortnite.domain.news.model.Notice
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val news: MutableLiveData<List<Notice>> = MutableLiveData()

    private var _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> get() = _loading

    private var _error: MutableLiveData<Boolean> = MutableLiveData(false)
    val error: LiveData<Boolean> get() = _error

    private val scope = CoroutineScope(Dispatchers.IO + Job())

    fun getNews() = scope.launch {
        newsRepository.getNews().collect {
            when (it) {
                is Resource.Loading -> {
                    _loading.postValue(true)
                }
                is Resource.Success -> {
                    _loading.postValue(false)
                    news.postValue(it.data)
                }
                is Resource.Error -> {
                    _loading.postValue(false)
                    _error.postValue(true)
                    Log.e("Erro:", it.toString())
                }
            }
        }
    }
}