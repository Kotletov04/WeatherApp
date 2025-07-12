package com.example.weatherapp.ui.components.city

import com.example.weatherapp.domain.models.ItemsModel

data class CityState(
    val isLoading: Boolean? = false,
    val error: String? = "",
    val city: List<ItemsModel> = emptyList(),
)