package com.example.weatherapp.data.mapper

import com.example.example.CityDto
import com.example.example.MetaDto
import com.example.weatherapp.data.remote.dto.ResultDto
import com.example.weatherapp.domain.models.CityModel
import com.example.weatherapp.domain.models.MetaModel
import com.example.weatherapp.domain.models.ResultModel
import javax.inject.Inject

class CityMapper @Inject constructor(
    private val metaMapper: Mapper<MetaModel, MetaDto>,
    private val resultMapper: Mapper<ResultModel, ResultDto>
): Mapper<CityModel, CityDto> {

    override fun toModel(from: CityDto): CityModel {
        return CityModel(
            meta = metaMapper.toModel(from = from.meta),
            result = resultMapper.toModel(from = from.result)
        )
    }

    override fun fromModel(model: CityModel): CityDto {
        return CityDto(
            meta = metaMapper.fromModel(model = model.meta),
            result = resultMapper.fromModel(model = model.result)
        )
    }

}