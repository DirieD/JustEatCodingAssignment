package com.example.justeatcodingassignment.domain

import com.example.justeatcodingassignment.data.RestaurantRepository
import com.example.justeatcodingassignment.data.model.Restaurant

class GetRestaurantsByPostcodeUseCase(
    private val repository: RestaurantRepository
) {
    suspend operator fun invoke(postcode: String): List<Restaurant> {
        return repository.getRestaurants(postcode)
    }
}