package com.wolking.fortnite.domain.stats.repository

import com.wolking.fortnite.data.core.NetworkResult
import com.wolking.fortnite.data.stats.data_source.StatsDto
import com.wolking.fortnite.data.core.service.BaseApiResponse
import com.wolking.fortnite.data.core.Resource

abstract class StatsRepository : BaseApiResponse() {
    abstract suspend fun getStats(name: String): Resource<NetworkResult<StatsDto>>
}