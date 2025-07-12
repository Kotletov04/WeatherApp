package com.example.weatherapp.data.repository

import com.example.example.CityDto
import com.example.weatherapp.data.mapper.CurrentCityMapper
import com.example.weatherapp.data.mapper.Mapper
import com.example.weatherapp.data.remote.service.CityRemoteService
import com.example.weatherapp.data.room.dao.CityDao
import com.example.weatherapp.data.room.dbo.CityDbo
import com.example.weatherapp.data.util.HttpStatusCode
import com.example.weatherapp.domain.models.CityModel
import com.example.weatherapp.domain.models.CurrentCityModel
import com.example.weatherapp.domain.models.CurrentWeatherModel
import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.domain.repository.CityRepository
import com.example.weatherapp.domain.util.ErrorMessages
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val cityMapper: Mapper<CityModel, CityDto>,
    private val cityRemoteService: CityRemoteService,
    private val currentCityMapper: Mapper<CurrentCityModel, CityDbo>,
    private val cityDao: CityDao
): CityRepository {

    override suspend fun getCityByName(q: String): CityModel? {
        val response = cityRemoteService.getCity(q = q)
        if (response.code() == HttpStatusCode.INTERNAL_SERVER_ERROR) throw Exception(ErrorMessages.internalServerError)
        if (response.code() == HttpStatusCode.OK) {
            val responseBody = response.body()!!
            when (responseBody.meta.code) {
                HttpStatusCode.OK -> {
                    return cityMapper.toModel(responseBody)
                }
                HttpStatusCode.NOT_FOUND -> {
                    throw Exception(ErrorMessages.notFound)
                }
                else -> {
                    return null
                }
            }
        } else {
            return null
        }


    }

    override suspend fun saveCity(currentCity: CurrentCityModel) {
        cityDao.insertCity(currentCityMapper.fromModel(currentCity))
    }

    override suspend fun getAllCityNames(): List<String> {
        return cityDao.getAllCity().map { it.cityName }
    }

}