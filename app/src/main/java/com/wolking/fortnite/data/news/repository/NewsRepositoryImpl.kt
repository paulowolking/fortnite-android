package com.wolking.fortnite.data.news.repository

import com.wolking.fortnite.data.core.NetworkResult
import com.wolking.fortnite.data.news.data_source.NewsDto
import com.wolking.fortnite.data.core.service.ApiService
import com.wolking.fortnite.domain.news.repository.NewsRepository
import com.wolking.fortnite.data.core.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepositoryImpl(
    private val apiService: ApiService
) : NewsRepository() {

    override suspend fun getNews(): Resource<NetworkResult<NewsDto>> {
        return withContext(Dispatchers.IO) {
            withContext(Dispatchers.IO) {
                safeApiCall { apiService.getNews() }
            }
        }
    }
}