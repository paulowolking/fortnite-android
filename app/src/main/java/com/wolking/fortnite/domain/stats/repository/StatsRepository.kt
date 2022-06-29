package com.wolking.fortnite.domain.stats.repository

import com.wolking.fortnite.data.core.service.BaseApiResponse
import com.wolking.fortnite.data.core.Resource
import com.wolking.fortnite.domain.stats.model.Stats
import kotlinx.coroutines.flow.Flow

abstract class StatsRepository : BaseApiResponse() {
    abstract suspend fun getStats(name: String): Flow<Resource<Stats?>>
}