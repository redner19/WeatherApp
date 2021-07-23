package com.rednerracaza.weatherapp.data.local

interface WeatherForecastLocal {
    fun isFavorite(id : String) : Boolean
    fun toggleFavorite(id : String)
}