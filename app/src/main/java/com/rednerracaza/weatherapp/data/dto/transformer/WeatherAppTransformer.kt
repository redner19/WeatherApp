package com.rednerracaza.weatherapp.data.dto.transformer

import com.rednerracaza.weatherapp.data.dto.response.MainDto
import com.rednerracaza.weatherapp.data.dto.response.WeatherDto
import com.rednerracaza.weatherapp.data.dto.response.WeatherForecastDto
import com.rednerracaza.weatherapp.data.model.MainModel
import com.rednerracaza.weatherapp.data.model.WeatherForecastModel
import com.rednerracaza.weatherapp.data.model.WeatherModel


fun MainDto.transform() =
    MainModel(
        temp = temp,
        temp_max = temp_max,
        temp_min = temp_min
    )

fun WeatherDto.transform() =
    WeatherModel(
        description = description,
        main = main
    )

fun WeatherForecastDto.transform(isFavorite: Boolean) =
    WeatherForecastModel(
        id = id,
        main = main.transform(),
        name = name,
        isFavorite = isFavorite,
        weather = weather.map { it.transform() },
    )