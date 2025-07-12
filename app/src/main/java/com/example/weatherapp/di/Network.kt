package com.example.weatherapp.di

import com.example.weatherapp.data.remote.service.CityRemoteService
import com.example.weatherapp.data.remote.service.WeatherRemoteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class Network {

    private val CITY_BASE_URL = "https://catalog.api.2gis.com/3.0/"

    private val WEATHER_BASE_URL = "https://api.weatherapi.com/"

    @Named("CITY_API")
    @Provides
    @Singleton
    fun provideCityRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(CITY_BASE_URL)
            .build()
    }

    @Named("WEATHER_API")
    @Provides
    @Singleton
    fun provideWeatherRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(WEATHER_BASE_URL)
            .build()
    }

    /*@Named("main")
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }*/

    @Provides
    @Singleton
    fun provideCityRemoteService(@Named("CITY_API") cityRetrofit: Retrofit): CityRemoteService {
        return cityRetrofit.create(CityRemoteService::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRemoteService(@Named("WEATHER_API") weatherRetrofit: Retrofit): WeatherRemoteService {
        return weatherRetrofit.create(WeatherRemoteService::class.java)
    }


}