package com.wolking.fortnite.data.models.shop.repository

import com.wolking.fortnite.data.base.NetworkResult
import com.wolking.fortnite.data.remote.service.ApiService
import com.wolking.fortnite.data.models.shop.model.Shop
import com.wolking.fortnite.domain.shop.repository.ShopRepository
import com.wolking.fortnite.presentation.Resource
import com.wolking.fortnite.utils.applyObservableAsync
import io.reactivex.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShopRepositoryImpl(
    private val apiService: ApiService
) : ShopRepository() {

    override suspend fun getShop(): Resource<NetworkResult<Shop>> {
        return withContext(Dispatchers.IO) {
            safeApiCall { apiService.getShop() }
        }
    }
}