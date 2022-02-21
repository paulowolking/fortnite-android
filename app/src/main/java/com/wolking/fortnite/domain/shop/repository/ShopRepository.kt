package com.wolking.fortnite.domain.shop.repository

import com.wolking.fortnite.data.base.Response
import com.wolking.fortnite.data.models.shop.model.Shop
import io.reactivex.Observable

abstract class ShopRepository {
    abstract fun getShop(): Observable<Response<Shop>>
}