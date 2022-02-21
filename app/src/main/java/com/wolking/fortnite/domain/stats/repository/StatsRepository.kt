package com.wolking.fortnite.domain.stats.repository

import com.wolking.fortnite.data.base.Response
import com.wolking.fortnite.data.models.stats.model.Stats
import io.reactivex.Observable

abstract class StatsRepository {
    abstract fun getStats(name: String) : Observable<Response<Stats>>
}