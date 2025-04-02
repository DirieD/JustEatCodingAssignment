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
                // Map the result to display name, star rating, address and cuisine
                _restaurantData.value = response.restaurants.map { restaurant ->
                    "${restaurant.name}     Rating: ${restaurant.rating?.starRating} Stars   Address: ${restaurant.address?.firstLine}  Cuisine: ${
                        getCuisineList(
                            restaurant
                        )
                    }"
                }
            } catch (e: Exception) {
                // Handle API errors
                _restaurantData.value = emptyList()
            }
        }
    }

    private fun getCuisineList(restaurant: Restaurant): String {
        val excludedKeywords =
            listOf(
                "Halal",
                "Low Delivery Fee",
                "Collect stamps",
                "Deals",
                "£8 off",
                "Local Legends",
                "Freebies",
                "Healthy"
            ) //These are all I could locate - could be missing some

        return restaurant.cuisineDetail
            ?.mapNotNull { it.name } // Extract cuisine names
            ?.filter { cuisine ->
                excludedKeywords.none {
                    cuisine.contains(
                        it,
                        ignoreCase = true
                    )
                }
            }
            ?.take(3) // Limit to first 3 cuisines
            ?.joinToString(", ") ?: "Unknown"
    }

}