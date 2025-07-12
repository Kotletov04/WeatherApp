package com.example.weatherapp.domain.models.forecast

data class ForecastDayModel(
    val date: String,
    val dateEpoch: Long,
    val day: DayModel
)