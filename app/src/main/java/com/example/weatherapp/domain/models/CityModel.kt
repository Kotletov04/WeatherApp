package com.example.weatherapp.domain.models

import com.example.example.MetaDto
import com.example.weatherapp.data.remote.dto.ResultDto


data class CityModel (
  val meta: MetaModel,
  val result: ResultModel,
)