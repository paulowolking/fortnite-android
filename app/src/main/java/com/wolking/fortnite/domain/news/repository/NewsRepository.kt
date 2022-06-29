package com.wolking.fortnite.domain.news.repository

import com.wolking.fortnite.data.core.service.BaseApiResponse
import com.wolking.fortnite.data.core.Resource
import com.wolking.fortnite.domain.news.model.Notice
import kotlinx.coroutines.flow.Flow

abstract class NewsRepository : BaseApiResponse() {
    abstract suspend fun getNews(): Flow<Resource<List<Notice>>>
}