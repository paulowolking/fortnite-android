package com.wolking.fortnite.domain.stats.repository

import com.wolking.fortnite.data.base.NetworkResult
import com.wolking.fortnite.data.models.stats.model.Stats
import com.wolking.fortnite.data.remote.service.BaseApiResponse
import com.wolking.fortnite.presentation.Resource

abstract class StatsRepository : BaseApiResponse() {
    abstract suspend fun getStats(name: String): Resource<NetworkResult<Stats>>
}