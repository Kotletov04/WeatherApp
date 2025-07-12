package com.example.weatherapp.data.mapper

import com.example.example.ItemsDto
import com.example.example.PointDto
import com.example.weatherapp.domain.models.ItemsModel
import com.example.weatherapp.domain.models.PointModel
import javax.inject.Inject

class ItemsMapper @Inject constructor(
    private val pointMapper: Mapper<PointModel, PointDto>
): Mapper<List<ItemsModel>, List<ItemsDto>> {
    override fun toModel(from: List<ItemsDto>): List<ItemsModel> {
        return from.map { ItemsModel(
            fullName = it.fullName,
            id = it.id,
            name = it.name,
            point = pointMapper.toModel(it.point),
            subtype = it.subtype,
            type = it.type
        ) }
    }

    override fun fromModel(model: List<ItemsModel>): List<ItemsDto> {
        return model.map { ItemsDto(
            fullName = it.fullName,
            id = it.id,
            name = it.name,
            point = pointMapper.fromModel(it.point),
            subtype = it.subtype,
            type = it.type
        ) }
    }
}