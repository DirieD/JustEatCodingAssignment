package com.example.justeatcodingassignment.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.justeatcodingassignment.presentation.model.RestaurantUIModel

@Composable
internal fun RestaurantInfo(
    restaurant: RestaurantUIModel
) {
    Text(
        text = "Cuisine: ${restaurant.cuisine}",
        modifier = Modifier.padding(8.dp),
        style = MaterialTheme.typography.bodyLarge
    )
    Text(
        text = "Rating: ${restaurant.rating} stars",
        modifier = Modifier.padding(8.dp),
        style = MaterialTheme.typography.bodyLarge
    )
    Text(
        text = "Address: ${restaurant.address}",
        modifier = Modifier.padding(8.dp),
        style = MaterialTheme.typography.bodyLarge
    )
}
