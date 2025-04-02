package com.example.justeatcodingassignment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _restaurantData =
        MutableLiveData<List<RestaurantUIModel>>()
    val restaurantData: LiveData<List<RestaurantUIModel>> get() = _restaurantData

    fun searchRestaurants(postcode: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.justEatAPIService.getRestaurantsByPostcode(postcode)
                _restaurantData.value = response.restaurants
                    .filter { it.name != null }
                    .map { restaurant ->
                        RestaurantUIModel(
                            name = restaurant.name ?: "Unknown",
                            rating = restaurant.rating?.starRating ?: 0f,
                            address = restaurant.address?.firstLine ?: "Unknown",
                            cuisine = getCuisineList(restaurant)
                        )
                    }
            } catch (e: Exception) {
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