package com.wolking.fortnite.data.models.news.repository

import com.wolking.fortnite.data.base.Response
import com.wolking.fortnite.data.models.news.model.News
import com.wolking.fortnite.data.remote.service.ApiService
import com.wolking.fortnite.domain.news.repository.NewsRepository
import com.wolking.fortnite.utils.applyObservableAsync
import io.reactivex.Observable

class NewsRepositoryImpl(
    private val apiService: ApiService
) : NewsRepository() {

    override fun getNews(): Observable<Response<News>> {
        return apiService.getNews()
            .compose(applyObservableAsync())
    }
}