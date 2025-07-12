package com.example.weatherapp.data.remote.dto.forecast

import com.google.gson.annotations.SerializedName

data class DayDto(
    @SerializedName("maxtemp_c")
    val maxTempC: Double,

    @SerializedName("condition")
    val condition: ForecastConditionDto,
)