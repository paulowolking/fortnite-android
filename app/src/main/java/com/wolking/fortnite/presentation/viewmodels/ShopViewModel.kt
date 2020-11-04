package com.wolking.fortnite.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import com.wolking.fortnite.data.AppRepository
import com.wolking.fortnite.data.model.Response
import com.wolking.fortnite.data.model.Shop
import com.wolking.fortnite.presentation.Resource

class ShopViewModel(
    private val appRepository: AppRepository
) : DisposingViewModel() {

    var shop = MutableLiveData<Resource<Response<Shop>>>()

    fun getShop(refresh: Boolean = false) {

        shop.value = Resource.Loading()

        addDisposable(appRepository.getShop().subscribe(
            { data ->
                shop.value = Resource.Success(data)
            },
            { error ->
                shop.value = Resource.Failure(error)
            }
        ))

    }
}