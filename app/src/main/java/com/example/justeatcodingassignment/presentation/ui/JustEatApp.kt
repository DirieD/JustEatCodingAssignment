package com.example.justeatcodingassignment.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.justeatcodingassignment.presentation.viewmodel.MainViewModel

@Composable
fun JustEatApp(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        SearchRestaurant(
            text = searchText,
            onTextChange = { searchText = it },
            onSearchClick = { viewModel.searchRestaurants(searchText) }
        )
        RestaurantList(viewModel = viewModel)
    }
}
