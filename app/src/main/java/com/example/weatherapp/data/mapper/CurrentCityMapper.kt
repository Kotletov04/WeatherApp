package com.example.weatherapp.data.mapper

import com.example.example.CityDto
import com.example.weatherapp.data.room.dbo.CityDbo
import com.example.weatherapp.domain.models.CurrentCityModel

class CurrentCityMapper: Mapper<CurrentCityModel, CityDbo> {

    override fun toModel(from: CityDbo): CurrentCityModel {
        return CurrentCityModel(
            /*id = from.id,*/
            cityName = from.cityName,
            lon = from.lon,
            lat = from.lat
        )
    }

    override fun fromModel(model: CurrentCityModel): CityDbo {
        return CityDbo(
            /*id = model.id,*/
            cityName = model.cityName,
            lon = model.lon,
            lat = model.lat
        )
    }
}