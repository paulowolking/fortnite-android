package com.wolking.fortnite.data.shop.repository

import com.wolking.fortnite.data.core.service.ApiService
import com.wolking.fortnite.domain.shop.repository.ShopRepository
import com.wolking.fortnite.data.core.Resource
import com.wolking.fortnite.domain.shop.model.Shop
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ShopRepositoryImpl(
    private val apiService: ApiService
) : ShopRepository() {

    override suspend fun getShop(): Flow<Resource<List<Shop>?>> =
        flow {
            emit(Resource.Loading())
            try {
                val response = apiService.getShop()
                emit(Resource.Success(response.data?.toDomain()))
            } catch (e: Exception) {
                emit(Resource.Error(e.toString()))
            }
        }
}