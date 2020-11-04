package com.wolking.fortnite.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.multidex.BuildConfig
import com.dimenuto.aboa.data.cache.AppPreferences
import com.wolking.fortnite.data.remote.service.ApiService
import com.dimenuto.aboa.data.remote.util.ApiInterceptor
import com.google.gson.GsonBuilder
import com.wolking.fortnite.data.AppRepository
import com.wolking.fortnite.presentation.viewmodels.HomeViewModel
import com.wolking.fortnite.presentation.viewmodels.NewsViewModel
import com.wolking.fortnite.presentation.viewmodels.ShopViewModel
import com.wolking.fortnite.util.BooleanTypeAdapter
import com.wolking.fortnite.util.DateTypeAdapter
import com.wolking.fortnite.util.NetworkUtil
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://fortnite-api.com/"

val modulesList by lazy {
    listOf(
        appModule,
        viewModelModule,
        dataModule
    )
}
val appModule = module {
    //    factory<SchedulerProvider.Factory> { AndroidSchedulerProvider() }
}

val viewModelModule: Module = module {

    viewModel { HomeViewModel(appRepository = get()) }
    viewModel { ShopViewModel(appRepository = get()) }
    viewModel { NewsViewModel(appRepository = get()) }
//    viewModel { OfertaViewModel(appRepository = get()) }
//    viewModel { PerfilViewModel(appRepository = get()) }
//    viewModel { CuponViewModel(appRepository = get()) }
//    viewModel { PagamentoViewModel(appRepository = get()) }
//    viewModel { RegistroViewModel(appRepository = get()) }

}

val dataModule: Module = module {
    single {
        AppRepository(
            apiService = get(),
            isNetworkAvailable = NetworkUtil.isNetworkAvailable(androidApplication().baseContext),
            appPreferences = AppPreferences(androidApplication())
        )
    }
    single { createRetrofit(sharedPreferences = get()).create(ApiService::class.java) }
    single { createPreferences(androidApplication()) }
}

private fun createPreferences(appContext: Context): SharedPreferences? {
    return PreferenceManager.getDefaultSharedPreferences(appContext)
}

private fun createRetrofit(sharedPreferences: SharedPreferences): Retrofit {

    return Retrofit.Builder()
        .client(
            OkHttpClient().newBuilder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(ApiInterceptor(sharedPreferences))
                .addInterceptor(HttpLoggingInterceptor().apply {
                    this.level =
                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                        else HttpLoggingInterceptor.Level.NONE
                })
                .build()
        )
        .baseUrl(BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .registerTypeAdapter(Date::class.java, DateTypeAdapter())
                    .registerTypeAdapter(Boolean::class.java, BooleanTypeAdapter())
                    .create()
            )
        )
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}