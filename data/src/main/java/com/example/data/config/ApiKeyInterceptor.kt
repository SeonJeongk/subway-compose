package com.example.data.config

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor private constructor(
    private val apiKey: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val newUrl = originalUrl.newBuilder()
            .addPathSegment(apiKey)
            .addPathSegment("json")
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }

    companion object {
        operator fun invoke(apiKey: String): ApiKeyInterceptor {
            return ApiKeyInterceptor(apiKey)
        }
    }
}
