package com.wolking.fortnite.presentation.shop.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wolking.fortnite.domain.shop.repository.ShopRepository
import com.wolking.fortnite.data.core.Resource
import com.wolking.fortnite.domain.shop.model.Shop
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

    var items: MutableLiveData<List<Shop>> = MutableLiveData()

    private val scope = CoroutineScope(Dispatchers.IO + Job())

    fun getShop() = scope.launch {
        shopRepository.getShop().collect {
            when (it) {
                is Resource.Loading -> {
                    _loading.postValue(true)
                }
                is Resource.Success -> {
                    _loading.postValue(false)
                    items.postValue(it.data)
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