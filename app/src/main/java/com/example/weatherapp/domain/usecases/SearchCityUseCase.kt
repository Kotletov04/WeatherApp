package com.example.weatherapp.domain.usecases

import com.example.weatherapp.domain.models.CityModel
import com.example.weatherapp.domain.models.ResultModel
import com.example.weatherapp.domain.repository.CityRepository
import com.example.weatherapp.domain.util.Resource
import com.example.weatherapp.domain.util.ErrorMessages
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException


class SearchCityUseCase(private val cityRepository: CityRepository) {

    operator fun invoke(city: String): Flow<Resource<ResultModel>> = flow {
        try {
            emit(Resource.Loading())
            val result = cityRepository.getCityByName(q = city)
            emit(Resource.Success(data = result!!.result))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: ErrorMessages.unknownError))
        } catch (e: IOException) {
            emit(Resource.Error(message = ErrorMessages.ioException))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: ErrorMessages.unknownError))
        }

    }
}