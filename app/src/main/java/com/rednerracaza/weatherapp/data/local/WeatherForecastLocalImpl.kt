package com.rednerracaza.weatherapp.data.local

import javax.inject.Inject

class WeatherForecastLocalImpl @Inject constructor(): WeatherForecastLocal {
    private val favorites : MutableMap<String,Boolean> = mutableMapOf()

    override fun isFavorite(id: String) = favorites[id] ?: false

    override fun toggleFavorite(id: String) {
        favorites[id] = isFavorite(id).not()
    }
}