package com.wolking.fortnite.domain.news.repository

import com.wolking.fortnite.data.news.data_source.NewsDto
import com.wolking.fortnite.data.core.NetworkResult
import com.wolking.fortnite.data.core.service.BaseApiResponse
import com.wolking.fortnite.data.core.Resource

abstract class NewsRepository : BaseApiResponse() {
    abstract suspend fun getNews(): Resource<NetworkResult<NewsDto>>
}