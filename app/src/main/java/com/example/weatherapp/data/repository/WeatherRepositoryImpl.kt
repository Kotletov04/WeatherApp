package com.example.weatherapp.data.repository

import com.example.weatherapp.data.mapper.Mapper
import com.example.weatherapp.data.mapper.forecast.ForecastResponseMapper
import com.example.weatherapp.data.remote.dto.WeatherDto
import com.example.weatherapp.data.remote.dto.forecast.ForecastDto
import com.example.weatherapp.data.remote.dto.forecast.ForecastResponseDto
import com.example.weatherapp.data.remote.service.WeatherRemoteService
import com.example.weatherapp.data.room.dao.ForecastDao
import com.example.weatherapp.data.room.dao.WeatherDao
import com.example.weatherapp.data.room.dbo.ForecastWeatherDbo
import com.example.weatherapp.data.room.dbo.WeatherDbo
import com.example.weatherapp.data.util.HttpStatusCode
import com.example.weatherapp.domain.models.CurrentWeatherModel
import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.domain.models.forecast.ForecastModel
import com.example.weatherapp.domain.models.forecast.ForecastResponseModel
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.util.ErrorMessages
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherRemoteService: WeatherRemoteService,
    private val weatherMapper: Mapper<WeatherModel, WeatherDto>,
    private val forecastMapper: Mapper<ForecastModel, ForecastDto>,
    private val forecastDao: ForecastDao,
    private val weatherDao: WeatherDao
): WeatherRepository {

    override suspend fun getCurrentWeather(q: String): WeatherModel {

        val response = weatherRemoteService.getWeather(q = q)
        if (response.code() == HttpStatusCode.INTERNAL_SERVER_ERROR) throw Exception(ErrorMessages.internalServerError)
        if (response.code() == HttpStatusCode.OK) {
            return weatherMapper.toModel(response.body()!!)
        } else throw Exception(ErrorMessages.notFound)
    }

    override suspend fun saveCurrentWeather(q: String, currentWeather: CurrentWeatherModel) {
        weatherDao.insertCurrentWeather(weatherDbo = WeatherDbo(
            tempC = currentWeather.tempC,
            isDay = currentWeather.isDay,
            conditionDto = currentWeather.conditionDto,
            windKph = currentWeather.windKph,
            windDegree = currentWeather.windDegree,
            pressureMb = currentWeather.pressureMb,
            humidity = currentWeather.humidity,
            cloud = currentWeather.cloud,
            feelslikeC = currentWeather.feelsLikeC,
            city = q,
            )
        )
    }

    override suspend fun getForecastWeather(q: String): ForecastModel {
        val response = weatherRemoteService.getForecastWeather(q = q)
        if (response.code() == HttpStatusCode.INTERNAL_SERVER_ERROR) throw Exception(ErrorMessages.internalServerError)
        if (response.code() == HttpStatusCode.OK) {
            return forecastMapper.toModel(response.body()!!.forecast)
        } else throw Exception(ErrorMessages.notFound)

    }

    override suspend fun saveForecastWeather(q: String, forecastModel: ForecastModel) {
        forecastDao.insertForecastWeather(forecastWeatherDbo = ForecastWeatherDbo(
            city = q,
            forecastDay = forecastModel.forecastDay
        ))
    }

    override suspend fun getCurrentWeatherFromDatabase(): WeatherModel {
        try {
            val resultFromDatabase = weatherDao.getWeatherFromDatabase()
            return WeatherModel(currentWeather = CurrentWeatherModel(
                tempC = resultFromDatabase.tempC,
                isDay = resultFromDatabase.isDay,
                conditionDto = resultFromDatabase.conditionDto,
                windKph = resultFromDatabase.windKph,
                windDegree = resultFromDatabase.windDegree,
                pressureMb = resultFromDatabase.pressureMb,
                humidity = resultFromDatabase.humidity,
                cloud = resultFromDatabase.cloud,
                feelsLikeC = resultFromDatabase.feelslikeC,
            ))
        } catch (e: Exception) {
            throw Exception(ErrorMessages.notFound)
        }
    }

    override suspend fun getForecastWeatherFromDatabase(): ForecastModel {
        try {
            val resultFromDatabase = forecastDao.getAllForecastWeather()
            return ForecastModel(forecastDay = resultFromDatabase.forecastDay)
        } catch (e: Exception) {
            throw Exception(ErrorMessages.notFound)
        }
    }


}