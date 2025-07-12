package com.example.weatherapp.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.data.room.dbo.WeatherDbo

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeather(weatherDbo: WeatherDbo)

    @Query("SELECT * FROM current_weather")
    suspend fun getWeatherFromDatabase(): WeatherDbo
}