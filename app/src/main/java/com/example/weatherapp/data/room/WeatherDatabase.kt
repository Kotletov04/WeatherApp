package com.example.weatherapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherapp.data.room.converter.Converters
import com.example.weatherapp.data.room.dao.CityDao
import com.example.weatherapp.data.room.dao.ForecastDao
import com.example.weatherapp.data.room.dao.WeatherDao
import com.example.weatherapp.data.room.dbo.CityDbo
import com.example.weatherapp.data.room.dbo.ForecastWeatherDbo
import com.example.weatherapp.data.room.dbo.WeatherDbo

@Database(entities = [CityDbo::class, WeatherDbo::class, ForecastWeatherDbo::class], version = 1)
@TypeConverters(Converters::class)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun cityDao(): CityDao
    abstract fun weatherDao(): WeatherDao
    abstract fun forecastWeatherDao(): ForecastDao
}