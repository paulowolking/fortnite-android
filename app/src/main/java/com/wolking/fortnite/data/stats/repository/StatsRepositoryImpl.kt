package com.wolking.fortnite.data.stats.repository

import com.wolking.fortnite.data.core.NetworkResult
import com.wolking.fortnite.data.core.service.ApiService
import com.wolking.fortnite.data.stats.data_source.StatsDto
import com.wolking.fortnite.domain.stats.repository.StatsRepository
import com.wolking.fortnite.data.core.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StatsRepositoryImpl(
    private val apiService: ApiService
) : StatsRepository() {

    override suspend fun getStats(name: String): Resource<NetworkResult<StatsDto>> {
        return withContext(Dispatchers.IO) {
            safeApiCall { apiService.getStats(name = name) }
        }
    }
}