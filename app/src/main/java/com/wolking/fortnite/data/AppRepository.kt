package com.wolking.fortnite.data

import com.dimenuto.aboa.data.cache.AppPreferences
import com.wolking.fortnite.data.model.News
import com.wolking.fortnite.data.model.Response
import com.wolking.fortnite.data.model.Shop
import com.wolking.fortnite.data.model.Stats
import com.wolking.fortnite.data.remote.service.ApiService
import com.wolking.fortnite.util.applyObservableAsync
import io.reactivex.Observable

class AppRepository(
    private val apiService: ApiService,
    private val isNetworkAvailable: Boolean,
    private val appPreferences: AppPreferences
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