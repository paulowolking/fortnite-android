package com.wolking.fortnite.domain.news.repository

import com.wolking.fortnite.data.models.news.model.News
import com.wolking.fortnite.data.base.NetworkResult
import com.wolking.fortnite.data.remote.service.BaseApiResponse
import com.wolking.fortnite.presentation.Resource

abstract class NewsRepository : BaseApiResponse() {
    abstract suspend fun getNews(): Resource<NetworkResult<News>>
}