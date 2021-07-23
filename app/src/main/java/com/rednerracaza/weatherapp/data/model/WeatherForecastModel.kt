package com.rednerracaza.weatherapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherForecastModel(
    val id: String,
    val main: MainModel,
    val name: String,
    var isFavorite: Boolean,
    val weather: List<WeatherModel>
) : Parcelable

