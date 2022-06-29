package com.wolking.fortnite.presentation.ui.shop.adapters

import com.wolking.fortnite.domain.shop.model.Shop

interface ItemAdapterListener {
    fun itemSelected(itemShop: Shop)
}