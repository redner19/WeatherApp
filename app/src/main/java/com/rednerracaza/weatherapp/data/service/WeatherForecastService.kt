package com.rednerracaza.weatherapp.data.service

import com.rednerracaza.weatherapp.data.dto.response.WeatherForecastDto
import com.rednerracaza.weatherapp.data.dto.response.WeatherForecastListResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherForecastService {

    @GET("data/2.5/group?units=metric")
    suspend fun getWeatherForecast(@Query("id", encoded = false) id: String):
            WeatherForecastListResponseDto

    @GET("data/2.5/weather?units=metric")
    suspend fun getWeatherForecastDetails(@Query("id") id: String?):
            WeatherForecastDto
}