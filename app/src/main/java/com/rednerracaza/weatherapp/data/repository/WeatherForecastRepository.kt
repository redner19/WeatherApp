package com.rednerracaza.weatherapp.data.repository

import com.rednerracaza.weatherapp.data.model.WeatherForecastModel

interface WeatherForecastRepository {
    suspend fun getWeatherForecastList(cityIds: List<String>): List<WeatherForecastModel>

    suspend fun getWeatherForecastDetails(id: String): WeatherForecastModel

    suspend fun toggleFavorite(id: String)
}