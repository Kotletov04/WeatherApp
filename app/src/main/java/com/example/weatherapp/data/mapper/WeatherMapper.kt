package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.remote.dto.CurrentWeatherDto
import com.example.weatherapp.data.remote.dto.WeatherDto
import com.example.weatherapp.data.room.dbo.WeatherDbo
import com.example.weatherapp.domain.models.CurrentWeatherModel
import com.example.weatherapp.domain.models.WeatherModel
import javax.inject.Inject

class WeatherMapper @Inject constructor(
    private val currentWeatherMapper: Mapper<CurrentWeatherModel, CurrentWeatherDto>
): Mapper<WeatherModel, WeatherDto> {
    override fun toModel(from: WeatherDto): WeatherModel {
        return WeatherModel(
            currentWeather = currentWeatherMapper.toModel(from.currentWeatherDto)
        )
    }

    override fun fromModel(model: WeatherModel): WeatherDto {
        return WeatherDto(
            currentWeatherDto = currentWeatherMapper.fromModel(model.currentWeather)
        )
    }
}