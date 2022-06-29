package com.wolking.fortnite.domain.shop.repository

import com.wolking.fortnite.data.core.service.BaseApiResponse
import com.wolking.fortnite.data.core.Resource
import com.wolking.fortnite.domain.shop.model.Shop
import kotlinx.coroutines.flow.Flow

abstract class ShopRepository : BaseApiResponse() {
    abstract suspend fun getShop(): Flow<Resource<List<Shop>?>>
}