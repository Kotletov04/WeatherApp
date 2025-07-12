package com.example.weatherapp.data.mapper

import com.example.example.MetaDto
import com.example.weatherapp.domain.models.MetaModel

class MetaMapper: Mapper<MetaModel, MetaDto> {
    override fun toModel(from: MetaDto): MetaModel {
        return MetaModel(apiVersion = from.apiVersion, code = from.code, issueDate = from.issueDate)
    }

    override fun fromModel(model: MetaModel): MetaDto {
        return MetaDto(apiVersion = model.apiVersion, code = model.code, issueDate = model.issueDate)
    }
}