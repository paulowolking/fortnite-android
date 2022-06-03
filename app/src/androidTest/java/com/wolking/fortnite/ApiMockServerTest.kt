package com.wolking.fortnite

import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

import org.junit.Test

import com.google.gson.Gson
import com.wolking.fortnite.data.news.repository.NewsRepositoryImpl
import com.wolking.fortnite.data.shop.repository.ShopRepositoryImpl
import com.wolking.fortnite.data.stats.repository.StatsRepositoryImpl
import com.wolking.fortnite.data.core.service.ApiService
import com.wolking.fortnite.data.core.Resource
import com.wolking.fortnite.utils.MockResponseFileReader
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(JUnit4::class)
class ApiMockServerTest {
    private val mockWebServer = MockWebServer()
    private val port = 8000

    lateinit var apiService: ApiService
    lateinit var statsRepositoryImpl: StatsRepositoryImpl
    lateinit var shopRepositoryImpl: ShopRepositoryImpl
    lateinit var newRepositoryImpl: NewsRepositoryImpl

    @Before
    fun init() {
        mockWebServer.start(port)
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(ApiService::class.java)

        statsRepositoryImpl = StatsRepositoryImpl(apiService)
        shopRepositoryImpl = ShopRepositoryImpl(apiService)
        newRepositoryImpl = NewsRepositoryImpl(apiService)
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

        when (val response = statsRepositoryImpl.getStats("wolking_")) {
            is Resource.Success -> {
//                assert(response.data)
            }
            is Resource.Error -> {
                assert(false)
            }
            else -> {}
        }
    }
}