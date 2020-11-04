package com.wolking.fortnite.ui.shop.adapters

import com.wolking.fortnite.data.model.Entries
import com.wolking.fortnite.data.model.Shop

interface ItemAdapterListener {
    fun itemSelected(entries: Entries)
}