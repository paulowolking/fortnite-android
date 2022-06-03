package com.wolking.fortnite.presentation.ui.shop.adapters

import com.wolking.fortnite.data.shop.data_source.EntriesDto

interface ItemAdapterListener {
    fun itemSelected(entriesDto: EntriesDto)
}