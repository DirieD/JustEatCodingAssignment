package com.example.justeatcodingassignment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _restaurantData =
        MutableLiveData<List<String>>(emptyList()) // List for restaurant data
    val restaurantData: LiveData<List<String>> get() = _restaurantData

    fun searchRestaurants(postcode: String) {
        viewModelScope.launch {
            try {
                // Fetch restaurant response based on postcode
                val response = RetrofitClient.justEatAPIService.getRestaurantsByPostcode(postcode)
                // Map the result to display name, star rating and address
                _restaurantData.value = response.restaurants.map { restaurant ->
                    "${restaurant.name}     Rating: ${restaurant.rating?.starRating} Stars   Address: ${restaurant.address?.firstLine}"
                }
            } catch (e: Exception) {
                // Handle API errors
                _restaurantData.value = emptyList()
            }
        }
    }
}