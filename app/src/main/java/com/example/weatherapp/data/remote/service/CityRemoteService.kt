package com.example.weatherapp.data.remote.service

import com.example.example.CityDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface CityRemoteService {

    @GET("items/geocode")
    suspend fun getCity(
        @Query("key") key: String = "538607e8-2c7d-4c13-adc3-7d9153a14513",
        @Query("fields") fields: String = "items.point",
        @Query("q") q: String,
        @Query("type") type: String = "adm_div.city"
    ): Response<CityDto>

}