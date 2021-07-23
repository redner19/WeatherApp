package com.rednerracaza.weatherapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherModel(
    val description: String,
    val main: String
) : Parcelable