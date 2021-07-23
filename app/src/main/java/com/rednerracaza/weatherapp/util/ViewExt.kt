package com.rednerracaza.weatherapp.util

import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.rednerracaza.weatherapp.R
import com.rednerracaza.weatherapp.data.model.MainModel

/*
* setBackgroundByTemp - will change the background colour of the cardview based on the temperature.
* */
fun CardView.setBackgroundByTemp(temp: Double) {
    val color = when {
        temp < 0.0 -> R.color.Freezing
        temp in 0.0..15.0 -> R.color.Cold
        temp in 15.0..30.0 -> R.color.Warm
        else -> R.color.Hot
    }
    this.setCardBackgroundColor(ContextCompat.getColor(this.context, color))
}

fun TextView.getFormattedTemp(main: MainModel, withHighLow: Boolean = false) {
    this.text = if (withHighLow)
        context.getString(
            R.string.formattedTemperatureHighLow,
            main.temp_max.formatTemp(context, false),
            main.temp_min.formatTemp(context, false)
        )
     else
        main.temp.formatTemp(context)

}




