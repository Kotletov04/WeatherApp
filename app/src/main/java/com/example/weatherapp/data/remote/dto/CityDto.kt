package com.example.example

import com.example.weatherapp.data.remote.dto.ResultDto
import com.google.gson.annotations.SerializedName


data class CityDto (
  @SerializedName("meta")
  val meta: MetaDto,
  @SerializedName("result")
  val result: ResultDto,
)