package com.wolking.fortnite

import org.junit.After
import org.junit.Before
import org.junit.Test
import com.google.gson.Gson
import com.wolking.fortnite.data.news.repository.NewsRepositoryImpl
import com.wolking.fortnite.data.shop.repository.ShopRepositoryImpl
import com.wolking.fortnite.data.stats.repository.StatsRepositoryImpl
import com.wolking.fortnite.data.core.service.ApiService
import com.wolking.fortnite.data.core.Resource
import com.wolking.fortnite.domain.news.repository.NewsRepository
import com.wolking.fortnite.domain.shop.repository.ShopRepository
import com.wolking.fortnite.domain.stats.repository.StatsRepository
import com.wolking.fortnite.utils.MockResponseFileReader
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiMockServerTest {
    private val mockWebServer = MockWebServer()
    private val port = 8000

    lateinit var apiService: ApiService
    lateinit var statsRepository: StatsRepository
    lateinit var shopRepository: ShopRepository
    lateinit var newRepository: NewsRepository

    @Before
    fun init() {
        mockWebServer.start(port)
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(ApiService::class.java)

        statsRepository = StatsRepositoryImpl(apiService)
        shopRepository = ShopRepositoryImpl(apiService)
        newRepository = NewsRepositoryImpl(apiService)
    }

    @After
    fun shutdown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testSuccessfulStatsResponse() = runBlocking {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(MockResponseFileReader("stats.json").content)
            }
        }

        statsRepository.getStats("wolking_").collect {
            when (it) {
                is Resource.Success -> {
                    assert(it.data != null)
                }
                is Resource.Error -> {
                    assert(false)
                }
                else -> {}
            }
        }
    }
}