package com.example.data.config

import android.util.Log
import com.example.data.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl: HttpUrl = originalRequest.url()

        val newUrl = originalUrl.newBuilder()
            .addPathSegment(BuildConfig.API_KEY)
            .addPathSegment("json")
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}

