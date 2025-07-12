package com.example.weatherapp.domain.models


data class ItemsModel(
    val fullName: String,
    val id: String,
    val name: String,
    val point: PointModel,
    val subtype: String? = "",
    val type: String,
)
