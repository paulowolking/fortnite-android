package com.wolking.fortnite.data.news.repository

import com.wolking.fortnite.data.core.Resource
import com.wolking.fortnite.data.core.service.ApiService
import com.wolking.fortnite.domain.news.repository.NewsRepository
import com.wolking.fortnite.domain.news.model.Notice
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepositoryImpl(
    private val apiService: ApiService
) : NewsRepository() {

    override suspend fun getNews(): Flow<Resource<List<Notice>>> =
        flow {
            emit(Resource.Loading())
            try {
                val response = apiService.getNews()
                val notices = arrayListOf<Notice>()
                for (notice in response.data?.motds!!) {
                    notices.add(notice.toDomain())
                }
                emit(Resource.Success(notices))
            } catch (e: Exception) {
                emit(Resource.Error(e.toString()))
            }
        }
}