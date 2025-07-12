package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.remote.dto.ConditionDto
import com.example.weatherapp.domain.models.ConditionModel

class ConditionMapper: Mapper<ConditionModel, ConditionDto> {
    override fun toModel(from: ConditionDto): ConditionModel {
        return ConditionModel(text = from.text, code = from.code)
    }

    override fun fromModel(model: ConditionModel): ConditionDto {
        return ConditionDto(text = model.text, code = model.code)
    }
}