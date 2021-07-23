package com.rednerracaza.weatherapp.view.forecast.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rednerracaza.weatherapp.databinding.ItemForecastBinding
import com.rednerracaza.weatherapp.data.model.WeatherForecastModel
import com.rednerracaza.weatherapp.util.getCityStatus
import com.rednerracaza.weatherapp.util.getFormattedTemp
import com.rednerracaza.weatherapp.util.setBackgroundByTemp

class ForecastPageAdapter(private val listener: OnItemClickListener) :
    ListAdapter<WeatherForecastModel, ForecastPageAdapter.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ItemForecastBinding
                .inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemForecastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            with(binding) {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION)
                        listener.onItemClicked(getItem(position).id)
                }
            }
        }

        fun bind(forecastItem: WeatherForecastModel) =
            with(binding) {
                with(forecastItem) {
                    weatherForecastPlaceTextView.text = name
                    weatherForecastTemperatureTextView.getFormattedTemp(main)
                    weatherForecastStatusTextView.text = this.getCityStatus()
                    weatherForecastFavoriteImageView.isVisible = isFavorite
                    forecastContainer.setBackgroundByTemp(main.temp)
                }
            }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.run { holder.bind(this) }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<WeatherForecastModel>() {
            override fun areItemsTheSame(
                oldItem: WeatherForecastModel,
                newItem: WeatherForecastModel
            ) = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: WeatherForecastModel,
                newItem: WeatherForecastModel
            ) = oldItem.id == newItem.id
        }
    }
}