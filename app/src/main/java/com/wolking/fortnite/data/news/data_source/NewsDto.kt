package com.wolking.fortnite.data.news.data_source

import com.wolking.fortnite.domain.news.model.Notice

class NewsDto {
    var motds: List<MotdDto> = arrayListOf()
}

class MotdDto(
    var title: String? = null,
    var tabTitle: String? = null,
    var body: String? = null,
    var image: String? = null,
    var tileImage: String? = null
) {
    fun toDomain(): Notice {
        return Notice(
            title = title,
            description = body,
            image = image
        )
    }
}