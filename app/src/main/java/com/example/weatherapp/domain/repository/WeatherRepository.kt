package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.models.CurrentWeatherModel
import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.domain.models.forecast.ForecastModel

interface WeatherRepository {

    suspend fun getCurrentWeather(q: String): WeatherModel
    suspend fun saveCurrentWeather(q: String, currentWeather: CurrentWeatherModel)

    suspend fun getForecastWeather(q: String): ForecastModel
    suspend fun saveForecastWeather(q: String, forecastModel: ForecastModel)

    suspend fun getCurrentWeatherFromDatabase(): WeatherModel
    suspend fun getForecastWeatherFromDatabase(): ForecastModel
}