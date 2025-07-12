package com.example.weatherapp.di

import com.example.weatherapp.domain.repository.CityRepository
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.usecases.GetCurrentWeatherUseCase
import com.example.weatherapp.domain.usecases.GetForecastWeatherUseCase
import com.example.weatherapp.domain.usecases.SaveCurrentCityUseCase
import com.example.weatherapp.domain.usecases.SearchCityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class Domain {

    @Provides
    @Singleton
    fun provideSearchCityUseCase(cityRepository: CityRepository): SearchCityUseCase {
        return SearchCityUseCase(cityRepository = cityRepository)
    }

    @Provides
    @Singleton
    fun provideSaveCurrentCityUseCase(cityRepository: CityRepository): SaveCurrentCityUseCase {
        return SaveCurrentCityUseCase(cityRepository = cityRepository)
    }

    @Provides
    @Singleton
    fun provideGetCurrentWeatherUseCase(weatherRepository: WeatherRepository): GetCurrentWeatherUseCase {
        return GetCurrentWeatherUseCase(weatherRepository = weatherRepository)
    }

    @Provides
    @Singleton
    fun provideForecastWeatherUseCase(weatherRepository: WeatherRepository): GetForecastWeatherUseCase {
        return GetForecastWeatherUseCase(weatherRepository = weatherRepository)
    }
}