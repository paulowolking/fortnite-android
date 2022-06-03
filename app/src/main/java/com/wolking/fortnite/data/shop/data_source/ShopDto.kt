package com.wolking.fortnite.data.shop.data_source

class ShopDto {
    var featured: FeaturedDto? = null
    var daily: FeaturedDto? = null
    var specialFeatured: FeaturedDto? = null
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