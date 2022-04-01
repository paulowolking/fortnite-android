package com.wolking.fortnite.data.models.news.repository

import com.wolking.fortnite.data.base.NetworkResult
import com.wolking.fortnite.data.models.news.model.News
import com.wolking.fortnite.data.remote.service.ApiService
import com.wolking.fortnite.domain.news.repository.NewsRepository
import com.wolking.fortnite.presentation.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepositoryImpl(
    private val apiService: ApiService
) : NewsRepository() {

    override suspend fun getNews(): Resource<NetworkResult<News>> {
        return withContext(Dispatchers.IO) {
            withContext(Dispatchers.IO) {
                safeApiCall { apiService.getNews() }
            }
        }
    }
}