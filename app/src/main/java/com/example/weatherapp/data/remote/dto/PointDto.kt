package com.example.example

import com.google.gson.annotations.SerializedName


data class PointDto (
  @SerializedName("lat")
  val lat: Double,
  @SerializedName("lon")
  val lon: Double

)