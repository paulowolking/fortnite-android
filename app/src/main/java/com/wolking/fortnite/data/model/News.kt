package com.wolking.fortnite.data.model

class News {
    var motds: List<Motd> = arrayListOf()
}

class Motd(
    var title: String? = null,
    var tabTitle: String? = null,
    var body: String? = null,
    var image: String? = null,
    var tileImage: String? = null
)