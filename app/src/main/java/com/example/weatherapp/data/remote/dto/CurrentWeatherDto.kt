package com.example.weatherapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CurrentWeatherDto(
    @SerializedName("temp_c")
    val tempC: Double,
    @SerializedName("is_day")
    val isDay: Int,
    @SerializedName("condition")
    val conditionDto: ConditionDto,
    @SerializedName("wind_kph")
    val windKph: Double,
    @SerializedName("wind_degree")
    val windDegree: Int,
    @SerializedName("pressure_mb")
    val pressureMb: Double,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("cloud")
    val cloud: Int,
    @SerializedName("feelslike_c")
    val feelslikeC: Double,

)
