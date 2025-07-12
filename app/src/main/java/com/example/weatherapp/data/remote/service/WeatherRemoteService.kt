package com.example.weatherapp.data.remote.service

import com.example.example.CityDto
import com.example.weatherapp.data.remote.dto.WeatherDto
import com.example.weatherapp.data.remote.dto.forecast.ForecastDto
import com.example.weatherapp.data.remote.dto.forecast.ForecastResponseDto
import com.example.weatherapp.domain.repository.WeatherRepository
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRemoteService {

    @GET("v1/current.json")
    suspend fun getWeather(
        @Query("key") key: String = "b63ba5f27e3647cabd5225416250307",
        @Query("q") q: String,
        @Query("aqi") aqi: String = "no",
        @Query("lang") lang: String = "ru"
    ): Response<WeatherDto>

    @GET("v1/forecast.json")
    suspend fun getForecastWeather(
        @Query("key") key: String = "b63ba5f27e3647cabd5225416250307",
        @Query("q") q: String,
        @Query("days") days: Int = 5,
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no",
        @Query("lang") lang: String = "ru"
    ): Response<ForecastResponseDto>

}

