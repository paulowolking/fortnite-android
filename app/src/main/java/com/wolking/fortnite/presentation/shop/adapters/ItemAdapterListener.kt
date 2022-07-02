package com.wolking.fortnite.presentation.shop.adapters

import com.wolking.fortnite.domain.shop.model.Shop

interface ItemAdapterListener {
    fun itemSelected(itemShop: Shop)
}