package com.wolking.fortnite.presentation.ui.shop.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wolking.fortnite.data.shop.data_source.EntriesDto
import com.wolking.fortnite.domain.shop.repository.ShopRepository
import com.wolking.fortnite.data.core.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val shopRepository: ShopRepository
) : ViewModel() {

    private var _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> get() = _loading

    private var _error: MutableLiveData<Boolean> = MutableLiveData(false)
    val error: LiveData<Boolean> get() = _error

    var items: MutableLiveData<List<EntriesDto>> = MutableLiveData()

    private val scope = CoroutineScope(Dispatchers.IO + Job())

    fun getShop() = scope.launch {
        when (val response = shopRepository.getShop()) {
            is Resource.Loading -> {
                _loading.postValue(true)
            }
            is Resource.Success -> {
                _loading.postValue(false)

                val entries: MutableList<EntriesDto> = mutableListOf()
                val shop = response.data.data
                shop?.featured?.entries?.let {
                    entries.addAll(it)
                }

                shop?.daily?.entries?.let {
                    entries.addAll(it)
                }

                shop?.specialFeatured?.entries?.let {
                    entries.addAll(it)
                }
                items.postValue(entries)
            }
            is Resource.Error -> {
                _loading.postValue(false)
                _error.postValue(true)
                Log.e("Erro:", response.toString())
            }
        }
    }

}