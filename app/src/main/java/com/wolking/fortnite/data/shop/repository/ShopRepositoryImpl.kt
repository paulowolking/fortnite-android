package com.wolking.fortnite.data.shop.repository

import com.wolking.fortnite.data.core.NetworkResult
import com.wolking.fortnite.data.core.service.ApiService
import com.wolking.fortnite.data.shop.data_source.ShopDto
import com.wolking.fortnite.domain.shop.repository.ShopRepository
import com.wolking.fortnite.data.core.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShopRepositoryImpl(
    private val apiService: ApiService
) : ShopRepository() {

    override suspend fun getShop(): Resource<NetworkResult<ShopDto>> {
        return withContext(Dispatchers.IO) {
            safeApiCall { apiService.getShop() }
        }
    }
}