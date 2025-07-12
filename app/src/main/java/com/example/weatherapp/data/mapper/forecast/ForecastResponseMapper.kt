package com.example.weatherapp.data.mapper.forecast

import com.example.weatherapp.data.mapper.Mapper
import com.example.weatherapp.data.remote.dto.forecast.ForecastDto
import com.example.weatherapp.data.remote.dto.forecast.ForecastResponseDto
import com.example.weatherapp.domain.models.forecast.ForecastModel
import com.example.weatherapp.domain.models.forecast.ForecastResponseModel
import javax.inject.Inject

class ForecastResponseMapper @Inject constructor(
    private val forecastMapper: Mapper<ForecastModel, ForecastDto>
) : Mapper<ForecastResponseModel, ForecastResponseDto> {
    override fun toModel(from: ForecastResponseDto): ForecastResponseModel {
        return ForecastResponseModel(
            forecast = forecastMapper.toModel(from.forecast)
        )
    }

    override fun fromModel(model: ForecastResponseModel): ForecastResponseDto {
        return ForecastResponseDto(
            forecast = forecastMapper.fromModel(model.forecast)
        )
    }
}