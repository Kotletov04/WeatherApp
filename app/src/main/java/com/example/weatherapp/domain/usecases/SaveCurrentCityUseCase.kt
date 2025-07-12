package com.example.weatherapp.domain.usecases

import com.example.weatherapp.domain.models.CityModel
import com.example.weatherapp.domain.models.CurrentCityModel
import com.example.weatherapp.domain.models.ItemsModel
import com.example.weatherapp.domain.models.ResultModel
import com.example.weatherapp.domain.repository.CityRepository
import com.example.weatherapp.domain.util.ErrorMessages
import com.example.weatherapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class SaveCurrentCityUseCase(private val cityRepository: CityRepository) {
    operator fun invoke(item: ItemsModel): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            cityRepository.saveCity(currentCity = CurrentCityModel(
                /*id = 0,*/
                cityName = item.name,
                lon = item.point.lon,
                lat = item.point.lat)
            )
            emit(Resource.Success(data = true))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: ErrorMessages.unknownError))
        } catch (e: IOException) {
            emit(Resource.Error(message = ErrorMessages.ioException))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: ErrorMessages.unknownError))
        }

    }
}