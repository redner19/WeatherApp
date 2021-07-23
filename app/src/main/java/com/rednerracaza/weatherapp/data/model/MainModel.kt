package com.rednerracaza.weatherapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainModel(
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
) : Parcelable