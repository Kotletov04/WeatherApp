package com.example.weatherapp.data.room.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.data.remote.dto.forecast.ForecastDayDto
import com.example.weatherapp.domain.models.forecast.ForecastDayModel
import com.google.gson.annotations.SerializedName

@Entity("forecast_weather")
data class ForecastWeatherDbo(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo("city")
    val city: String,
    @ColumnInfo("forecastday")
    val forecastDay: List<ForecastDayModel>
)
