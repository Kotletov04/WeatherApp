package com.example.weatherapp.data.mapper

import com.example.example.PointDto
import com.example.weatherapp.domain.models.PointModel

class PointMapper: Mapper<PointModel, PointDto> {
    override fun toModel(from: PointDto): PointModel {
        return PointModel(
            lat = from.lat,
            lon = from.lon
        )
    }

    override fun fromModel(model: PointModel): PointDto {
        return PointDto(
            lat = model.lat,
            lon = model.lon
        )
    }


}