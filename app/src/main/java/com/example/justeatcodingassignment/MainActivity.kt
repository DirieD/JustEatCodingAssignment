package com.example.justeatcodingassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.justeatcodingassignment.ui.theme.JustEatCodingAssignmentTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JustEatCodingAssignmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RestaurantListScreen(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        // Trigger the API request with sample postcode
        viewModel.searchRestaurants("EC4M 7RF")
    }
}

@Composable
fun RestaurantListScreen(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val restaurants by viewModel.restaurantData.observeAsState(emptyList())

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Restaurants Near You", style = MaterialTheme.typography.headlineSmall)

        val limitedRestaurants = restaurants.take(10) // Limits to 10

        if (limitedRestaurants.isEmpty()) {
            Text(text = "No restaurants found", modifier = Modifier.padding(top = 8.dp))
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(limitedRestaurants) { restaurant ->
                    RestaurantItem(restaurantName = restaurant)
                }
            }
        }
    }
}


@Composable
fun RestaurantItem(restaurantName: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = restaurantName,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RestaurantListPreview() {
    JustEatCodingAssignmentTheme {
        RestaurantListScreen(viewModel = MainViewModel())
    }
}
