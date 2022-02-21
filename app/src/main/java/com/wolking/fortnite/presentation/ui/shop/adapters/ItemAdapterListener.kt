package com.wolking.fortnite.presentation.ui.shop.adapters

import com.wolking.fortnite.data.models.shop.model.Entries

interface ItemAdapterListener {
    fun itemSelected(entries: Entries)
}