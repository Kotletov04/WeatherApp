package com.example.weatherapp.domain.models


data class ResultModel(
    val items: List<ItemsModel>,
    val total: Int
)
