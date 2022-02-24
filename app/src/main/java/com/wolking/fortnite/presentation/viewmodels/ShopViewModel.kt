package com.wolking.fortnite.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import com.wolking.fortnite.data.base.Response
import com.wolking.fortnite.data.models.shop.model.Shop
import com.wolking.fortnite.domain.shop.repository.ShopRepository
import com.wolking.fortnite.presentation.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val shopRepository: ShopRepository
) : DisposingViewModel() {

    var shop = MutableLiveData<Resource<Response<Shop>>>()

    fun getShop(refresh: Boolean = false) {

        shop.value = Resource.Loading()

        addDisposable(shopRepository.getShop().subscribe(
            { data ->
                shop.value = Resource.Success(data)
            },
            { error ->
                shop.value = Resource.Failure(error)
            }
        ))

    }
}