package com.example.weatherapp.data.room.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.data.remote.dto.ConditionDto
import com.example.weatherapp.domain.models.ConditionModel
import com.google.gson.annotations.SerializedName

@Entity(tableName = "current_weather")
data class WeatherDbo(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo("city")
    val city: String,
    @ColumnInfo("temp_c")
    val tempC: Double,
    @ColumnInfo("is_day")
    val isDay: Int,
    @ColumnInfo("condition")
    val conditionDto: ConditionModel,
    @ColumnInfo("wind_kph")
    val windKph: Double,
    @ColumnInfo("wind_degree")
    val windDegree: Int,
    @ColumnInfo("pressure_mb")
    val pressureMb: Double,
    @ColumnInfo("humidity")
    val humidity: Int,
    @ColumnInfo("cloud")
    val cloud: Int,
    @ColumnInfo("feelslike_c")
    val feelslikeC: Double,


)
