package com.wolking.fortnite.data.models.shop.repository

import com.wolking.fortnite.data.base.Response
import com.wolking.fortnite.data.remote.service.ApiService
import com.wolking.fortnite.data.models.shop.model.Shop
import com.wolking.fortnite.domain.shop.repository.ShopRepository
import com.wolking.fortnite.utils.applyObservableAsync
import io.reactivex.Observable

class ShopRepositoryImpl(
    private val apiService: ApiService
) : ShopRepository() {

    override fun getShop(): Observable<Response<Shop>> {
        return apiService.getShop()
            .compose(applyObservableAsync())
    }
}