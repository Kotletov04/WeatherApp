package com.example.example

import com.google.gson.annotations.SerializedName


data class MetaDto (
  @SerializedName("api_version")
  val apiVersion: String,
  @SerializedName("code")
  val code: Int,
  @SerializedName("issue_date")
  val issueDate: String
)