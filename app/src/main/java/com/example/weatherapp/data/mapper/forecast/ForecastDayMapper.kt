package com.example.weatherapp.data.mapper.forecast

import com.example.weatherapp.data.mapper.Mapper
import com.example.weatherapp.data.remote.dto.forecast.DayDto
import com.example.weatherapp.data.remote.dto.forecast.ForecastDayDto
import com.example.weatherapp.domain.models.forecast.DayModel
import com.example.weatherapp.domain.models.forecast.ForecastDayModel
import javax.inject.Inject

class ForecastDayMapper @Inject constructor(
    private val dayMapper: Mapper<DayModel, DayDto>
) : Mapper<ForecastDayModel, ForecastDayDto> {
    override fun toModel(from: ForecastDayDto): ForecastDayModel {
        return ForecastDayModel(
            date = from.date,
            dateEpoch = from.dateEpoch,
            day = dayMapper.toModel(from.day)
        )
    }

    override fun fromModel(model: ForecastDayModel): ForecastDayDto {
        return ForecastDayDto(
            date = model.date,
            dateEpoch = model.dateEpoch,
            day = dayMapper.fromModel(model.day)
        )
    }
}