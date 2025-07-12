package com.example.weatherapp.domain.usecases

import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.util.ErrorMessages
import com.example.weatherapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCurrentWeatherUseCase(private val weatherRepository: WeatherRepository) {

    operator fun invoke(city: String): Flow<Resource<WeatherModel>> = flow {
        try {
            emit(Resource.Loading())
            val result = weatherRepository.getCurrentWeather(q = city)
            weatherRepository.saveCurrentWeather(q = city, currentWeather = result.currentWeather)
            emit(Resource.Success(data = result))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: ErrorMessages.unknownError))
        } catch (e: IOException) {
            val result = weatherRepository.getCurrentWeatherFromDatabase()
            emit(Resource.Success(data = result))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: ErrorMessages.unknownError))
        }

    }

}