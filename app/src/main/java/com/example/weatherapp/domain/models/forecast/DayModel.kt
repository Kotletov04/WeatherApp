package com.example.weatherapp.domain.models.forecast

data class DayModel(
    val maxTempC: Double,
    val condition: ForecastConditionModel,
)