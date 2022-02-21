package com.wolking.fortnite.data.models.stats.repository

import com.wolking.fortnite.data.base.Response
import com.wolking.fortnite.data.remote.service.ApiService
import com.wolking.fortnite.data.models.stats.model.Stats
import com.wolking.fortnite.domain.stats.repository.StatsRepository
import com.wolking.fortnite.utils.applyObservableAsync
import io.reactivex.Observable

class StatsRepositoryImpl(
    private val apiService: ApiService
) : StatsRepository() {

    override fun getStats(name: String): Observable<Response<Stats>> {
        return apiService.getStats(name = name)
            .compose(applyObservableAsync())
    }
}