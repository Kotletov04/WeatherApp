package com.example.weatherapp.domain.models

data class CurrentWeatherModel(
    val tempC: Double,
    val isDay: Int,
    val conditionDto: ConditionModel,
    val windKph: Double,
    val windDegree: Int,
    val pressureMb: Double,
    val humidity: Int,
    val cloud: Int,
    val feelsLikeC: Double,

)
