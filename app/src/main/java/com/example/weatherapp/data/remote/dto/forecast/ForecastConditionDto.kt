package com.example.weatherapp.data.remote.dto.forecast

import com.google.gson.annotations.SerializedName

data class ForecastConditionDto(
    @SerializedName("text")
    val text: String,

    @SerializedName("icon")
    val icon: String,

    @SerializedName("code")
    val code: Int
)