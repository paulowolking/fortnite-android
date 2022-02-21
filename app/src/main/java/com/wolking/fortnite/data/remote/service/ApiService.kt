package com.wolking.fortnite.data.remote.service

import com.wolking.fortnite.data.models.news.model.News
import com.wolking.fortnite.data.base.Response
import com.wolking.fortnite.data.models.shop.model.Shop
import com.wolking.fortnite.data.models.stats.model.Stats
import retrofit2.http.GET
import io.reactivex.Observable
import retrofit2.http.Query


interface ApiService {

    @GET("v1/stats/br/v2")
    fun getStats(
        @Query("name") name: String
    ): Observable<Response<Stats>>

    @GET("v2/shop/br")
    fun getShop(
        @Query("language") name: String = "pt-BR"
    ): Observable<Response<Shop>>

    @GET("v2/news/br")
    fun getNews(
        @Query("language") name: String = "pt-BR"
    ): Observable<Response<News>> }