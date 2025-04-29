package com.example.justeatcodingassignment.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.justeatcodingassignment.R
import com.example.justeatcodingassignment.presentation.ui.components.RestaurantItem
import com.example.justeatcodingassignment.presentation.viewmodel.MainViewModel

@Composable
fun RestaurantList(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val restaurants by viewModel.restaurantData.collectAsState()

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = stringResource(R.string.restaurants_near),
            style = MaterialTheme.typography.headlineSmall
        )

        val limitedRestaurants = restaurants.take(10)

        if (limitedRestaurants.isEmpty()) {
            Text(
                text = stringResource(R.string.restaurants_not_found),
                modifier = Modifier.padding(top = 8.dp)
            )
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(limitedRestaurants) { restaurant ->
                    RestaurantItem(
                        restaurant = restaurant
                    )
                }
            }
        }
    }
}