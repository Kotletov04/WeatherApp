package com.example.weatherapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ConditionDto(
    @SerializedName("text")
    val text: String,
    @SerializedName("code")
    val code: Int
)
