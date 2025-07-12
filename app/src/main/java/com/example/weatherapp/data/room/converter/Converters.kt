package com.example.weatherapp.data.room.converter

import androidx.room.TypeConverter
import com.example.weatherapp.data.remote.dto.ConditionDto
import com.example.weatherapp.domain.models.ConditionModel
import com.example.weatherapp.domain.models.forecast.ForecastDayModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class Converters {

    @TypeConverter
    fun currentWeatherConditionToString(condition: ConditionModel): String {
        return Gson().toJson(condition)
    }

    @TypeConverter
    fun stringToCurrentWeatherCondition(string: String): ConditionModel {
        val type = object : TypeToken<ConditionModel>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun forecastWeatherDayToString(forecastDay: List<ForecastDayModel>): String {
        return Gson().toJson(forecastDay)
    }

    @TypeConverter
    fun stringToForecastWeatherDay(string: String): List<ForecastDayModel> {
        val type = object : TypeToken<List<ForecastDayModel>>() {}.type
        return Gson().fromJson(string, type)
    }
}