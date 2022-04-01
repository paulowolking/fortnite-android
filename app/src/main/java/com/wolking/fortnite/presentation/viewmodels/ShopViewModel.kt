package com.wolking.fortnite.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wolking.fortnite.data.base.NetworkResult
import com.wolking.fortnite.data.models.shop.model.Shop
import com.wolking.fortnite.domain.shop.repository.ShopRepository
import com.wolking.fortnite.presentation.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val shopRepository: ShopRepository
) : ViewModel() {

    var shop: MutableLiveData<Resource<NetworkResult<Shop>>> = MutableLiveData()

    fun getShop() = CoroutineScope(Dispatchers.Main).launch {
        shop.value = withContext(Dispatchers.Default) {
            shopRepository.getShop()
        }
    }
}