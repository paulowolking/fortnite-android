package com.wolking.fortnite.data.core.util

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ApiInterceptor constructor(val sharedPreferences: SharedPreferences) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
//        val accessToken = sharedPreferences
//            .getString("accessToken", null)

        val request = chain.request()
        val urlBuilder = request.url.newBuilder()
        val requestBuilder = request.newBuilder()
            .method(request.method, request.body)
            .header("Accept", "application/json")
            .header("Content-type", "application/json")
        requestBuilder.header("Authorization", "bc80a798-8a0f-4184-aff9-8ead63f44fa0")
        return chain.proceed(requestBuilder.url(urlBuilder.build()).build())
    }

}