package com.wolking.fortnite.presentation.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wolking.fortnite.data.stats.data_source.StatsDto
import com.wolking.fortnite.domain.stats.repository.StatsRepository
import com.wolking.fortnite.data.core.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val statsRepository: StatsRepository
) : ViewModel() {

    var statsDto: MutableLiveData<StatsDto> = MutableLiveData()

    private var _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> get() = _loading

    private var _error: MutableLiveData<Boolean> = MutableLiveData(false)
    val error: LiveData<Boolean> get() = _error

    private val scope = CoroutineScope(Dispatchers.IO + Job())

    fun getStats(name: String) = CoroutineScope(Dispatchers.Main).launch {
        scope.launch {
            when (val response = statsRepository.getStats(name = name)) {
                is Resource.Loading -> {
                    _loading.postValue(true)
                }
                is Resource.Success -> {
                    _loading.postValue(false)

                    response.data.data?.let { items ->
                        statsDto.postValue(items)
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

}