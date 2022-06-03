package com.wolking.fortnite.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.multidex.BuildConfig
import androidx.room.Room
import com.wolking.fortnite.data.core.util.ApiInterceptor
import com.google.gson.GsonBuilder
import com.wolking.fortnite.data.database.AppDatabase
import com.wolking.fortnite.data.database.models.friends.repository.FriendRepositoryImpl
import com.wolking.fortnite.data.news.repository.NewsRepositoryImpl
import com.wolking.fortnite.data.shop.repository.ShopRepositoryImpl
import com.wolking.fortnite.data.stats.repository.StatsRepositoryImpl
import com.wolking.fortnite.data.core.service.ApiService
import com.wolking.fortnite.domain.friends.repository.FriendsRepository
import com.wolking.fortnite.domain.news.repository.NewsRepository
import com.wolking.fortnite.domain.shop.repository.ShopRepository
import com.wolking.fortnite.domain.stats.repository.StatsRepository
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
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    fun providesNewsRepository(apiService: ApiService): NewsRepository =
        NewsRepositoryImpl(apiService)

    @Provides
    fun providesShopRepository(apiService: ApiService): ShopRepository =
        ShopRepositoryImpl(apiService)

    @Provides
    fun providesStatsRepository(apiService: ApiService): StatsRepository =
        StatsRepositoryImpl(apiService)

    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "fortinaticos"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providesFriendsRepository(appDatabase: AppDatabase): FriendsRepository =
        FriendRepositoryImpl(appDatabase)

}