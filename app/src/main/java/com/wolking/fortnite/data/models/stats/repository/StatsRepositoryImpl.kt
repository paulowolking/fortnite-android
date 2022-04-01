package com.wolking.fortnite.data.models.stats.repository

import com.wolking.fortnite.data.base.NetworkResult
import com.wolking.fortnite.data.remote.service.ApiService
import com.wolking.fortnite.data.models.stats.model.Stats
import com.wolking.fortnite.domain.stats.repository.StatsRepository
import com.wolking.fortnite.presentation.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StatsRepositoryImpl(
    private val apiService: ApiService
) : StatsRepository() {

    override suspend fun getStats(name: String): Resource<NetworkResult<Stats>> {
        return withContext(Dispatchers.IO) {
            safeApiCall { apiService.getStats(name = name) }
        }
    }
}