package com.wolking.fortnite.data.shop.data_source

import com.wolking.fortnite.domain.shop.model.Shop

class ShopDto {
    var featured: FeaturedDto? = null
    var daily: FeaturedDto? = null
    var specialFeatured: FeaturedDto? = null

    fun toDomain(): List<Shop> {
        val itemsShop = arrayListOf<Shop>()
        featured?.entries?.forEach {
            itemsShop.add(
                Shop(
                    title = it.items.firstOrNull()?.name,
                    photoUrl = it.items.firstOrNull()?.images?.featured
                        ?: it.items.firstOrNull()?.images?.icon,
                    value = it.finalPrice,
                )
            )
        }

        daily?.entries?.forEach {
            itemsShop.add(
                Shop(
                    title = it.items.firstOrNull()?.name,
                    photoUrl = it.items.firstOrNull()?.images?.featured
                        ?: it.items.firstOrNull()?.images?.icon,
                    value = it.finalPrice,
                )
            )
        }

        specialFeatured?.entries?.forEach {
            itemsShop.add(
                Shop(
                    title = it.items.firstOrNull()?.name,
                    photoUrl = it.items.firstOrNull()?.images?.featured
                        ?: it.items.firstOrNull()?.images?.icon,
                    value = it.finalPrice,
                )
            )
        }

        return itemsShop
    }
}

class FeaturedDto(
    var name: String? = null,
    var entries: List<EntriesDto> = arrayListOf()
)

class EntriesDto(
    var regularPrice: Int = 0,
    var finalPrice: Int = 0,
    var items: List<ItemDto> = arrayListOf()
)

class ItemDto(
    var name: String? = null,
    var description: String? = null,
    var images: ImageDto? = null
)

class ImageDto(
    var smallIcon: String? = null,
    var icon: String? = null,
    var featured: String? = null
)