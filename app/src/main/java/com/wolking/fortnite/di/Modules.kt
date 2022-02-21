package com.wolking.fortnite.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.multidex.BuildConfig
import com.wolking.fortnite.data.remote.util.ApiInterceptor
import com.google.gson.GsonBuilder
import com.wolking.fortnite.data.AppRepository
import com.wolking.fortnite.data.remote.service.ApiService
import com.wolking.fortnite.utils.BooleanTypeAdapter
import com.wolking.fortnite.utils.DateTypeAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val BASE_URL = "https://fortnite-api.com/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    @Singleton
    @Provides
    fun provideHttpClient(sharedPreferences: SharedPreferences): OkHttpClient {
        return OkHttpClient
            .Builder()
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
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create(
            GsonBuilder()
                .registerTypeAdapter(Date::class.java, DateTypeAdapter())
                .registerTypeAdapter(Boolean::class.java, BooleanTypeAdapter())
                .create()
        )

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    fun providesAppRepository(apiService: ApiService): AppRepository = AppRepository(apiService)

}