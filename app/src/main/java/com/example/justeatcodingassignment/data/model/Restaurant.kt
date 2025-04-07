package com.example.justeatcodingassignment.data.model

import com.squareup.moshi.Json

data class RestaurantResponse(
    val restaurants: List<Restaurant>, val resultCount: Int? = null, val area: String? = null
)

data class Restaurant(
    val name: String?,
    @Json(name = "cuisines") val cuisineDetail: List<CuisineDetail>?,
    val rating: Rating?,
    val address: Address?
)

data class CuisineDetail(
    val name: String?
)

data class Address(
    val city: String?, val firstLine: String?, val postcode: String?
)

data class Rating(
    val count: Int?, val average: Double?, val starRating: Double?
)
