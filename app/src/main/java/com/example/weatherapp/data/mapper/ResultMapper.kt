package com.example.weatherapp.data.mapper

import com.example.example.ItemsDto
import com.example.example.PointDto
import com.example.weatherapp.data.remote.dto.ResultDto
import com.example.weatherapp.domain.models.ItemsModel
import com.example.weatherapp.domain.models.PointModel
import com.example.weatherapp.domain.models.ResultModel
import javax.inject.Inject

class ResultMapper @Inject constructor(
    private val itemsMapper: Mapper<List<ItemsModel>, List<ItemsDto>>,
): Mapper<ResultModel, ResultDto> {

    override fun toModel(from: ResultDto): ResultModel {
        return ResultModel(
            items = itemsMapper.toModel(from = from.items),
            total = from.total
        )
    }

    override fun fromModel(model: ResultModel): ResultDto {
        return ResultDto(
            items = itemsMapper.fromModel(model = model.items),
            total = model.total
        )
    }
}