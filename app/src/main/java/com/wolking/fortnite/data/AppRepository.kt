package com.wolking.fortnite.data

import com.wolking.fortnite.data.models.news.model.News
import com.wolking.fortnite.data.base.Response
import com.wolking.fortnite.data.models.shop.model.Shop
import com.wolking.fortnite.data.models.stats.model.Stats
import com.wolking.fortnite.data.remote.service.ApiService
import com.wolking.fortnite.utils.applyObservableAsync
import io.reactivex.Observable
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getStats(name: String): Observable<Response<Stats>> {
        return apiService.getStats(name = name)
            .compose(applyObservableAsync())
    }

    fun getShop(): Observable<Response<Shop>> {
        return apiService.getShop()
            .compose(applyObservableAsync())
    }

    fun getNews(): Observable<Response<News>> {
        return apiService.getNews()
            .compose(applyObservableAsync())
    }
}