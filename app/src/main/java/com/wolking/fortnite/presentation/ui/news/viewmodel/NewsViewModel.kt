package com.wolking.fortnite.presentation.ui.news.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wolking.fortnite.data.news.data_source.MotdDto
import com.wolking.fortnite.domain.news.repository.NewsRepository
import com.wolking.fortnite.data.core.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    var news: MutableLiveData<List<MotdDto>> = MutableLiveData()

    private var _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> get() = _loading

    private var _error: MutableLiveData<Boolean> = MutableLiveData(false)
    val error: LiveData<Boolean> get() = _error

    private val scope = CoroutineScope(Dispatchers.IO + Job())

    fun getNews() = scope.launch {
        when (val response = newsRepository.getNews()) {
            is Resource.Loading -> {
                _loading.postValue(true)
            }
            is Resource.Success -> {
                _loading.postValue(false)

                response.data.data?.motds?.let { items ->
                    news.postValue(items)
                }
            }
            is Resource.Error -> {
                _loading.postValue(false)
                _error.postValue(true)
                Log.e("Erro:", response.toString())
            }
        }
    }
}