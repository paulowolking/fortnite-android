package com.wolking.fortnite.data.remote.service

import com.wolking.fortnite.data.models.news.model.News
import com.wolking.fortnite.data.base.NetworkResult
import com.wolking.fortnite.data.models.shop.model.Shop
import com.wolking.fortnite.data.models.stats.model.Stats
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("v1/stats/br/v2")
    suspend fun getStats(
        @Query("name") name: String
    ): Response<NetworkResult<Stats>>

    @GET("v2/shop/br")
    suspend fun getShop(
        @Query("language") name: String = "pt-BR"
    ): Response<NetworkResult<Shop>>

    @GET("v2/news/br")
    suspend fun getNews(
        @Query("language") name: String = "pt-BR"
    ): Response<NetworkResult<News>>
}