package com.example.weatherapp.ui.components.weather

import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.domain.models.forecast.ForecastModel

data class WeatherState(
    val isLoading: Boolean? = false,
    val error: String? = "",
    val currentWeather: WeatherModel? = null,
)