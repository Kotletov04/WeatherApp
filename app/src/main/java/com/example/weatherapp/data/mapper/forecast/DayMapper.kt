package com.example.weatherapp.data.mapper.forecast

import com.example.weatherapp.data.mapper.Mapper
import com.example.weatherapp.data.remote.dto.forecast.ForecastConditionDto
import com.example.weatherapp.data.remote.dto.forecast.DayDto
import com.example.weatherapp.domain.models.forecast.ForecastConditionModel
import com.example.weatherapp.domain.models.forecast.DayModel
import javax.inject.Inject

class DayMapper @Inject constructor(
    private val conditionMapper: Mapper<ForecastConditionModel, ForecastConditionDto>
) : Mapper<DayModel, DayDto> {
    override fun toModel(from: DayDto): DayModel {
        return DayModel(
            maxTempC = from.maxTempC,
            condition = conditionMapper.toModel(from.condition),
        )
    }

    override fun fromModel(model: DayModel): DayDto {
        return DayDto(
            maxTempC = model.maxTempC,
            condition = conditionMapper.fromModel(model.condition),
        )
    }
}