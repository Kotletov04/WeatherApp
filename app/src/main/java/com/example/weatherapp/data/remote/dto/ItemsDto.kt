package com.example.example

import com.google.gson.annotations.SerializedName


data class ItemsDto (

  @SerializedName("full_name")
  val fullName: String,
  @SerializedName("id")
  val id: String,
  @SerializedName("name")
  val name: String,
  @SerializedName("point")
  val point: PointDto,
  @SerializedName("subtype")
  val subtype: String? = "",
  @SerializedName("type")
  val type: String,

)