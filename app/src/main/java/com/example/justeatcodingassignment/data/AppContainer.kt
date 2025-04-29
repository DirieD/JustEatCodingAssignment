package com.example.justeatcodingassignment.data

import com.example.justeatcodingassignment.data.network.JustEatAPIService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface AppContainer {
    val restaurantRepository: RestaurantRepository
}

class DefaultAppContainer : AppContainer {

    private val baseUrl = "https://uk.api.just-eat.io"

    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()

    private val apiService: JustEatAPIService by lazy {
        retrofit.create(JustEatAPIService::class.java)
    }

    override val restaurantRepository: RestaurantRepository by lazy {
        NetworkRestaurantRepository(apiService)
    }
}