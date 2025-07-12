package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.example.CityDto
import com.example.example.ItemsDto
import com.example.example.PointDto
import com.example.example.MetaDto
import com.example.weatherapp.data.mapper.CityMapper
import com.example.weatherapp.data.mapper.ConditionMapper
import com.example.weatherapp.data.mapper.CurrentCityMapper
import com.example.weatherapp.data.mapper.CurrentWeatherMapper
import com.example.weatherapp.data.mapper.ItemsMapper
import com.example.weatherapp.data.mapper.Mapper
import com.example.weatherapp.data.mapper.MetaMapper
import com.example.weatherapp.data.mapper.PointMapper
import com.example.weatherapp.data.mapper.forecast.DayMapper
import com.example.weatherapp.data.mapper.ResultMapper
import com.example.weatherapp.data.mapper.WeatherMapper
import com.example.weatherapp.data.mapper.forecast.ForecastConditionMapper
import com.example.weatherapp.data.mapper.forecast.ForecastDayMapper
import com.example.weatherapp.data.mapper.forecast.ForecastMapper
import com.example.weatherapp.data.mapper.forecast.ForecastResponseMapper
import com.example.weatherapp.data.remote.dto.ConditionDto
import com.example.weatherapp.data.remote.dto.CurrentWeatherDto
import com.example.weatherapp.data.remote.dto.ResultDto
import com.example.weatherapp.data.remote.dto.WeatherDto
import com.example.weatherapp.data.remote.dto.forecast.DayDto
import com.example.weatherapp.data.remote.dto.forecast.ForecastConditionDto
import com.example.weatherapp.data.remote.dto.forecast.ForecastDayDto
import com.example.weatherapp.data.remote.dto.forecast.ForecastDto
import com.example.weatherapp.data.remote.dto.forecast.ForecastResponseDto
import com.example.weatherapp.data.remote.service.CityRemoteService
import com.example.weatherapp.data.remote.service.WeatherRemoteService
import com.example.weatherapp.data.repository.CityRepositoryImpl
import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.data.room.WeatherDatabase
import com.example.weatherapp.data.room.dao.CityDao
import com.example.weatherapp.data.room.dao.ForecastDao
import com.example.weatherapp.data.room.dao.WeatherDao
import com.example.weatherapp.data.room.dbo.CityDbo
import com.example.weatherapp.data.room.dbo.ForecastWeatherDbo
import com.example.weatherapp.domain.models.CityModel
import com.example.weatherapp.domain.models.ConditionModel
import com.example.weatherapp.domain.models.CurrentCityModel
import com.example.weatherapp.domain.models.CurrentWeatherModel
import com.example.weatherapp.domain.models.ItemsModel
import com.example.weatherapp.domain.models.MetaModel
import com.example.weatherapp.domain.models.PointModel
import com.example.weatherapp.domain.models.ResultModel
import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.domain.models.forecast.DayModel
import com.example.weatherapp.domain.models.forecast.ForecastConditionModel
import com.example.weatherapp.domain.models.forecast.ForecastDayModel
import com.example.weatherapp.domain.models.forecast.ForecastModel
import com.example.weatherapp.domain.models.forecast.ForecastResponseModel
import com.example.weatherapp.domain.repository.CityRepository
import com.example.weatherapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class Data {

    @Provides
    @Singleton
    fun provideCityRepository(
        cityRemoteService: CityRemoteService,
        cityMapper: Mapper<CityModel, CityDto>,
        cityDao: CityDao,
        currentCityMapper: Mapper<CurrentCityModel, CityDbo>
    ): CityRepository {
        return CityRepositoryImpl(
            cityMapper = cityMapper,
            cityRemoteService = cityRemoteService,
            cityDao = cityDao,
            currentCityMapper = currentCityMapper)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(
        weatherRemoteService: WeatherRemoteService,
        weatherMapper: Mapper<WeatherModel, WeatherDto>,
        forecastMapper: Mapper<ForecastModel, ForecastDto>,
        forecastDao: ForecastDao,
        weatherDao: WeatherDao
    ): WeatherRepository {
        return WeatherRepositoryImpl(
            weatherRemoteService = weatherRemoteService,
            weatherMapper = weatherMapper,
            forecastMapper = forecastMapper,
            forecastDao = forecastDao,
            weatherDao = weatherDao
        )
    }

    @Provides
    @Singleton
    fun provideForecastDao(weatherDatabase: WeatherDatabase): ForecastDao {
        return weatherDatabase.forecastWeatherDao()
    }

    @Provides
    @Singleton
    fun provideWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao {
        return weatherDatabase.weatherDao()
    }

    @Provides
    @Singleton
    fun provideCityDao(weatherDatabase: WeatherDatabase): CityDao {
        return weatherDatabase.cityDao()
    }

    @Provides
    @Singleton
    fun provideWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = WeatherDatabase::class.java,
            name = "weather.db"
        )
            .createFromAsset("databases/weather.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideCityMapper(
        metaMapper: Mapper<MetaModel, MetaDto>,
        resultMapper: Mapper<ResultModel, ResultDto>
    ): Mapper<CityModel, CityDto> {
        return CityMapper(metaMapper = metaMapper, resultMapper = resultMapper)
    }

    @Provides
    @Singleton
    fun provideItemsMapper(pointMapper: Mapper<PointModel, PointDto>): Mapper<List<ItemsModel>, List<ItemsDto>> {
        return ItemsMapper(pointMapper = pointMapper)
    }

    @Provides
    @Singleton
    fun providePointMapper(): Mapper<PointModel, PointDto> {
        return PointMapper()
    }

    @Provides
    @Singleton
    fun provideMetaMapper(): Mapper<MetaModel, MetaDto> {
        return MetaMapper()
    }

    @Provides
    @Singleton
    fun provideResultMapper(itemsMapper: Mapper<List<ItemsModel>, List<ItemsDto>>): Mapper<ResultModel, ResultDto> {
        return ResultMapper(itemsMapper = itemsMapper)
    }

    @Provides
    @Singleton
    fun provideCurrentCityMapper(): Mapper<CurrentCityModel, CityDbo> {
        return CurrentCityMapper()
    }

    @Provides
    @Singleton
    fun provideConditionMapper(): Mapper<ConditionModel, ConditionDto> {
        return ConditionMapper()
    }

    @Provides
    @Singleton
    fun provideWeatherMapper(currentWeatherMapper: Mapper<CurrentWeatherModel, CurrentWeatherDto>): Mapper<WeatherModel, WeatherDto> {
        return WeatherMapper(currentWeatherMapper = currentWeatherMapper)
    }

    @Provides
    @Singleton
    fun provideCurrentWeatherMapper(conditionMapper: Mapper<ConditionModel, ConditionDto>): Mapper<CurrentWeatherModel, CurrentWeatherDto> {
        return CurrentWeatherMapper(conditionMapper = conditionMapper)
    }
    @Provides
    @Singleton
    fun provideForecastConditionMapper(): Mapper<ForecastConditionModel, ForecastConditionDto> {
        return ForecastConditionMapper()
    }

    @Provides
    @Singleton
    fun provideDayMapper(
        forecastConditionMapper: Mapper<ForecastConditionModel, ForecastConditionDto>
    ): Mapper<DayModel, DayDto> {
        return DayMapper(conditionMapper = forecastConditionMapper)
    }

    @Provides
    @Singleton
    fun provideForecastDayMapper(
        dayMapper: Mapper<DayModel, DayDto>
    ): Mapper<ForecastDayModel, ForecastDayDto> {
        return ForecastDayMapper(dayMapper = dayMapper)
    }
    @Provides
    @Singleton
    fun provideForecastMapper(
        forecastDayMapper: Mapper<ForecastDayModel, ForecastDayDto>
    ): Mapper<ForecastModel, ForecastDto> {
        return ForecastMapper(forecastDayMapper = forecastDayMapper)
    }

    @Provides
    @Singleton
    fun provideForecastResponseMapper(
        forecastMapper: Mapper<ForecastModel, ForecastDto>
    ): Mapper<ForecastResponseModel, ForecastResponseDto> {
        return ForecastResponseMapper(forecastMapper = forecastMapper)
    }


}