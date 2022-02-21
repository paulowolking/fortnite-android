package com.wolking.fortnite.domain.news.repository

import com.wolking.fortnite.data.models.news.model.News
import com.wolking.fortnite.data.base.Response
import io.reactivex.Observable

abstract class NewsRepository {
    abstract fun getNews(): Observable<Response<News>>
}