package com.example.weatherapp.data.mapper

interface Mapper<M, E> {
    fun toModel(from: E): M
    fun fromModel(model: M): E
}