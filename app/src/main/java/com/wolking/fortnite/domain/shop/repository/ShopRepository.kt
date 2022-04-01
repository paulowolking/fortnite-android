package com.wolking.fortnite.domain.shop.repository

import com.wolking.fortnite.data.base.NetworkResult
import com.wolking.fortnite.data.models.shop.model.Shop
import com.wolking.fortnite.data.remote.service.BaseApiResponse
import com.wolking.fortnite.presentation.Resource

abstract class ShopRepository : BaseApiResponse() {
    abstract suspend fun getShop(): Resource<NetworkResult<Shop>>
}