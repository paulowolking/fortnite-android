package com.wolking.fortnite

import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

import org.junit.Test

import com.google.gson.Gson
import com.wolking.fortnite.data.models.news.repository.NewsRepositoryImpl
import com.wolking.fortnite.data.models.shop.repository.ShopRepositoryImpl
import com.wolking.fortnite.data.models.stats.repository.StatsRepositoryImpl
import com.wolking.fortnite.data.remote.service.ApiService
import com.wolking.fortnite.utils.MockResponseFileReader
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(JUnit4::class)
class ApiMockServerTest {
    private val mockWebServer = MockWebServer()
    private val MOCK_WEBSERVER_PORT = 8000

    lateinit var apiService: ApiService
    lateinit var statsRepositoryImpl: StatsRepositoryImpl
    lateinit var shopRepositoryImpl: ShopRepositoryImpl
    lateinit var newRepositoryImpl: NewsRepositoryImpl

    @Before
    fun init() {
        mockWebServer.start(MOCK_WEBSERVER_PORT)
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
    fun checkStatsRepository() {
        mockWebServer.apply {
            enqueue(MockResponse().setBody(MockResponseFileReader("stats.json").content))
        }
        statsRepositoryImpl.getStats("wolking_")
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertComplete()
            .assertValueCount(1)
            .assertValue { it.data != null }
            .assertNoErrors()
    }

    @Test
    fun checkShopRepository() {
        mockWebServer.apply {
            enqueue(MockResponse().setBody(MockResponseFileReader("shop.json").content))
        }
        shopRepositoryImpl.getShop()
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertComplete()
            .assertValueCount(1)
            .assertValue { it.data != null }
            .assertNoErrors()
    }

    @Test
    fun checkNewsRepository() {
        mockWebServer.apply {
            enqueue(MockResponse().setBody(MockResponseFileReader("news.json").content))
        }
        newRepositoryImpl.getNews()
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertComplete()
            .assertValueCount(1)
            .assertValue { it.data != null }
            .assertNoErrors()
    }
}