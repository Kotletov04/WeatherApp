package com.example.weatherapp.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.data.room.dbo.ForecastWeatherDbo


@Dao
interface ForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForecastWeather(forecastWeatherDbo: ForecastWeatherDbo)

    @Query("SELECT * FROM forecast_weather")
    fun getAllForecastWeather(): ForecastWeatherDbo
}