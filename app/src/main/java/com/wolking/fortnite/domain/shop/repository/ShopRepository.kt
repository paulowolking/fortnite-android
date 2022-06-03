package com.wolking.fortnite.domain.shop.repository

import com.wolking.fortnite.data.core.NetworkResult
import com.wolking.fortnite.data.shop.data_source.ShopDto
import com.wolking.fortnite.data.core.service.BaseApiResponse
import com.wolking.fortnite.data.core.Resource

abstract class ShopRepository : BaseApiResponse() {
    abstract suspend fun getShop(): Resource<NetworkResult<ShopDto>>
}