package com.jherrera.data.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModule {
    private val logInterceptor by lazy {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        logInterceptor
    }

    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .build()
    }

    private fun getRetrofit(endpointURL: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(endpointURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    fun createHousecallProAPI(endpointURL: String): HousecallProService {
        val retrofit = getRetrofit(endpointURL)
        return retrofit.create(HousecallProService::class.java)
    }
}