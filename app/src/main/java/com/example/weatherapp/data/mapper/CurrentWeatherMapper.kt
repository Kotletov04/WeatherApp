package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.remote.dto.ConditionDto
import com.example.weatherapp.data.remote.dto.CurrentWeatherDto
import com.example.weatherapp.data.room.dbo.WeatherDbo
import com.example.weatherapp.domain.models.ConditionModel
import com.example.weatherapp.domain.models.CurrentWeatherModel
import javax.inject.Inject

class CurrentWeatherMapper @Inject constructor(
    private val conditionMapper: Mapper<ConditionModel, ConditionDto>
): Mapper<CurrentWeatherModel, CurrentWeatherDto> {
    override fun toModel(from: CurrentWeatherDto): CurrentWeatherModel {
        return CurrentWeatherModel(
            tempC = from.tempC,
            isDay = from.isDay,
            conditionDto = conditionMapper.toModel(from.conditionDto),
            windKph = from.windKph,
            windDegree = from.windDegree,
            pressureMb = from.pressureMb,
            humidity = from.humidity,
            cloud = from.cloud,
            feelsLikeC = from.feelslikeC,
        )
    }

    override fun fromModel(model: CurrentWeatherModel): CurrentWeatherDto {
        return CurrentWeatherDto(
            tempC = model.tempC,
            isDay = model.isDay,
            conditionDto = conditionMapper.fromModel(model.conditionDto),
            windKph = model.windKph,
            windDegree = model.windDegree,
            pressureMb = model.pressureMb,
            humidity = model.humidity,
            cloud = model.cloud,
            feelslikeC = model.feelsLikeC,
        )
    }
}