package com.example.justeatcodingassignment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.justeatcodingassignment.JustEatApplication
import com.example.justeatcodingassignment.data.RestaurantRepository
import com.example.justeatcodingassignment.data.model.Restaurant
import com.example.justeatcodingassignment.data.network.RetrofitClient
import com.example.justeatcodingassignment.presentation.model.RestaurantUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: RestaurantRepository
) : ViewModel() {

    private val _restaurantData =
        MutableStateFlow<List<RestaurantUIModel>>(emptyList())
    val restaurantData: StateFlow<List<RestaurantUIModel>> = _restaurantData

    fun searchRestaurants(postcode: String) {
        viewModelScope.launch {
            try {
                val restaurants = repository.getRestaurants(postcode)
                _restaurantData.value = restaurants
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

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as JustEatApplication)
                val repository = application.container.restaurantRepository
                MainViewModel(repository)
            }
        }
    }
}