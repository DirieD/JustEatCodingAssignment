package com.example.justeatcodingassignment.presentation.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.justeatcodingassignment.R

@Composable
fun SearchRestaurant(text: String, onTextChange: (String) -> Unit, onSearchClick: () -> Unit) {
    Row(modifier = Modifier.padding(10.dp)) {
        TextField(
            value = text,
            onValueChange = onTextChange,
            label = { Text(text = stringResource(R.string.search_postcode)) },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = onSearchClick,
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Text(text = stringResource(R.string.search_text))
        }
    }
}