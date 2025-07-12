package com.example.weatherapp.data.remote.dto

import com.example.example.ItemsDto
import com.google.gson.annotations.SerializedName

data class ResultDto(
    @SerializedName("items")
    val items: List<ItemsDto>,
    @SerializedName("total")
    val total: Int
)
