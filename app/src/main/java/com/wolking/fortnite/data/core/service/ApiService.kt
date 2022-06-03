package com.wolking.fortnite.data.core.service

import com.wolking.fortnite.data.news.data_source.NewsDto
import com.wolking.fortnite.data.core.NetworkResult
import com.wolking.fortnite.data.shop.data_source.ShopDto
import com.wolking.fortnite.data.stats.data_source.StatsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("v1/stats/br/v2")
    suspend fun getStats(
        @Query("name") name: String
    ): Response<NetworkResult<StatsDto>>

    @GET("v2/shop/br")
    suspend fun getShop(
        @Query("language") name: String = "pt-BR"
    ): Response<NetworkResult<ShopDto>>

    @GET("v2/news/br")
    suspend fun getNews(
        @Query("language") name: String = "pt-BR"
    ): Response<NetworkResult<NewsDto>>
}