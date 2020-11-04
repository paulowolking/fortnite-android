package com.wolking.fortnite.data.model

class Shop {
    var featured: Featured? = null
    var daily: Featured? = null
    var specialFeatured: Featured? = null
}

class Featured(
    var name: String? = null,
    var entries: List<Entries> = arrayListOf()
)

class Entries(
    var regularPrice: Int = 0,
    var finalPrice: Int = 0,
    var items: List<Item> = arrayListOf()
)

class Item(
    var name: String? = null,
    var description: String? = null,
    var images: Image? = null
)

class Image(
    var smallIcon: String? = null,
    var icon: String? = null,
    var featured: String? = null
)