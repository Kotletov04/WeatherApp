package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.models.CityModel
import com.example.weatherapp.domain.models.CurrentCityModel
import com.example.weatherapp.domain.models.WeatherModel

interface CityRepository {

    suspend fun getCityByName(q: String): CityModel?
    suspend fun saveCity(currentCity: CurrentCityModel)
    suspend fun getAllCityNames(): List<String>

}