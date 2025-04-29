package com.example.justeatcodingassignment.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.justeatcodingassignment.ui.theme.JustEatCodingAssignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JustEatCodingAssignmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    JustEatApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun RestaurantListPreview() {
    JustEatCodingAssignmentTheme {
        JustEatApp(viewModel = MainViewModel(), modifier = Modifier)
    }
}*/
