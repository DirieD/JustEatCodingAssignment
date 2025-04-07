package com.example.justeatcodingassignment.data.network

import com.example.justeatcodingassignment.data.model.RestaurantResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface JustEatAPIService {

    @GET("/discovery/uk/restaurants/enriched/bypostcode/{postcode}")
    suspend fun getRestaurantsByPostcode(@Path("postcode") postcode: String): RestaurantResponse
}