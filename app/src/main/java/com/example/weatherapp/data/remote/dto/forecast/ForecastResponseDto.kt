package com.example.weatherapp.data.remote.dto.forecast

import com.google.gson.annotations.SerializedName

data class ForecastResponseDto(
    @SerializedName("forecast")
    val forecast: ForecastDto
)







