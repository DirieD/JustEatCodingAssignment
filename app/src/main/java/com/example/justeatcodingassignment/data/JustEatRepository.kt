package com.example.justeatcodingassignment.data

import com.example.justeatcodingassignment.data.model.Restaurant
import com.example.justeatcodingassignment.data.network.JustEatAPIService

interface RestaurantRepository {
    suspend fun getRestaurants(postcode: String): List<Restaurant>
}

class NetworkRestaurantRepository(
    private val apiService: JustEatAPIService
) : RestaurantRepository {
    override suspend fun getRestaurants(postcode: String): List<Restaurant> {
        return apiService.getRestaurantsByPostcode(postcode).restaurants
    }
}
