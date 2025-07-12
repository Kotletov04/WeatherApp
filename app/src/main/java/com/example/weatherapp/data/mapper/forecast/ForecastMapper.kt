package com.example.weatherapp.data.mapper.forecast

import com.example.weatherapp.data.mapper.Mapper
import com.example.weatherapp.data.remote.dto.forecast.ForecastDayDto
import com.example.weatherapp.data.remote.dto.forecast.ForecastDto
import com.example.weatherapp.domain.models.forecast.ForecastDayModel
import com.example.weatherapp.domain.models.forecast.ForecastModel
import javax.inject.Inject

class ForecastMapper @Inject constructor(
    private val forecastDayMapper: Mapper<ForecastDayModel, ForecastDayDto>
) : Mapper<ForecastModel, ForecastDto> {
    override fun toModel(from: ForecastDto): ForecastModel {
        return ForecastModel(
            forecastDay = from.forecastDay.map { forecastDayMapper.toModel(it) }
        )
    }

    override fun fromModel(model: ForecastModel): ForecastDto {
        return ForecastDto(
            forecastDay = model.forecastDay.map { forecastDayMapper.fromModel(it) }
        )
    }
}