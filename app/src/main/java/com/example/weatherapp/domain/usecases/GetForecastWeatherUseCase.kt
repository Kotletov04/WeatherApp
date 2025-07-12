package com.example.weatherapp.domain.usecases

import com.example.weatherapp.domain.models.CurrentCityModel
import com.example.weatherapp.domain.models.ItemsModel
import com.example.weatherapp.domain.models.forecast.ForecastModel
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.util.ErrorMessages
import com.example.weatherapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetForecastWeatherUseCase(private val weatherRepository: WeatherRepository) {
    operator fun invoke(city: String): Flow<Resource<ForecastModel>> = flow {
        try {
            emit(Resource.Loading())
            val result = weatherRepository.getForecastWeather(q = city)
            weatherRepository.saveForecastWeather(q = city, forecastModel = result)
            emit(Resource.Success(data = result))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: ErrorMessages.unknownError))
        } catch (e: IOException) {
            val result = weatherRepository.getForecastWeatherFromDatabase()
            emit(Resource.Success(data = result))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: ErrorMessages.unknownError))
        }

    }
}