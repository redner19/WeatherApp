package com.rednerracaza.weatherapp.util

import android.content.Context
import com.rednerracaza.weatherapp.R
import com.rednerracaza.weatherapp.data.model.WeatherForecastModel
import com.rednerracaza.weatherapp.util.Constants.NotAvailable

fun Double.formatTemp(context: Context, hasDecimal: Boolean = true) =
    context.getString(
        R.string.formattedTemperature, String.format(
            if (hasDecimal)
                context.getString(R.string.singleDecimal)
            else
                context.getString(R.string.noDecimal), this
        )
    )

fun WeatherForecastModel.getCityStatus() =
    try {
        this.weather.first().main
    } catch (e: Exception) {
        NotAvailable
    }
