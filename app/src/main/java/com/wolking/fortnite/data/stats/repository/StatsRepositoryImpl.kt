package com.wolking.fortnite.data.stats.repository

import com.wolking.fortnite.data.core.service.ApiService
import com.wolking.fortnite.domain.stats.repository.StatsRepository
import com.wolking.fortnite.data.core.Resource
import com.wolking.fortnite.domain.stats.model.Stats
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StatsRepositoryImpl(
    private val apiService: ApiService
) : StatsRepository() {

    override suspend fun getStats(name: String): Flow<Resource<Stats?>> =
        flow {
            emit(Resource.Loading())
            try {
                val response = apiService.getStats(name = name)
                emit(Resource.Success(response.data?.toDomain()))
            } catch (e: Exception) {
                emit(Resource.Error(e.toString()))
            }
        }
}