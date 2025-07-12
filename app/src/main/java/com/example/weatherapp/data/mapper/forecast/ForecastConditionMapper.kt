package com.example.weatherapp.data.mapper.forecast

import com.example.weatherapp.data.mapper.Mapper
import com.example.weatherapp.data.remote.dto.forecast.ForecastConditionDto
import com.example.weatherapp.domain.models.forecast.ForecastConditionModel

class ForecastConditionMapper: Mapper<ForecastConditionModel, ForecastConditionDto> {
    override fun toModel(from: ForecastConditionDto): ForecastConditionModel {
        return ForecastConditionModel(
            text = from.text,
            icon = from.icon,
            code = from.code
        )
    }

    override fun fromModel(model: ForecastConditionModel): ForecastConditionDto {
        return ForecastConditionDto(
            text = model.text,
            icon = model.icon,
            code = model.code
        )
    }
}