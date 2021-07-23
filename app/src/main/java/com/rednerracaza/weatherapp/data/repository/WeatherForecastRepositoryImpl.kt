package com.rednerracaza.weatherapp.data.repository

import com.rednerracaza.weatherapp.data.dto.transformer.transform
import com.rednerracaza.weatherapp.data.local.WeatherForecastLocal
import com.rednerracaza.weatherapp.data.model.WeatherForecastModel
import com.rednerracaza.weatherapp.data.service.WeatherForecastService
import javax.inject.Inject

class WeatherForecastRepositoryImpl @Inject constructor(
    private val local: WeatherForecastLocal,
    private val service: WeatherForecastService
) : WeatherForecastRepository {

    override suspend fun getWeatherForecastList(cityIds: List<String>):
            List<WeatherForecastModel> =
            service
                .getWeatherForecast(cityIds.joinToString(","))
                .list
                .map { it.transform(local.isFavorite(it.id)) }

    override suspend fun getWeatherForecastDetails(id: String):
            WeatherForecastModel =
            service.getWeatherForecastDetails(id)
                .transform(local.isFavorite(id))

    override suspend fun toggleFavorite(id: String) =
        local.toggleFavorite(id)
}