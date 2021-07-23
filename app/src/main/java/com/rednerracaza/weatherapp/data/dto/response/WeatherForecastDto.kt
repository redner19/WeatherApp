package com.rednerracaza.weatherapp.data.dto.response

data class WeatherForecastDto(
    val id: String,
    val main: MainDto,
    val name: String,
    val weather: List<WeatherDto>
)

