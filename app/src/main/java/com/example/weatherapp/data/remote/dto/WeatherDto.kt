package com.example.weatherapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WeatherDto(
    @SerializedName("current")
    val currentWeatherDto: CurrentWeatherDto
)
