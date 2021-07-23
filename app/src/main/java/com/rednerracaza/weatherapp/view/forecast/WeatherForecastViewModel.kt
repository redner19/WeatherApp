package com.rednerracaza.weatherapp.view.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rednerracaza.weatherapp.data.model.WeatherForecastModel
import com.rednerracaza.weatherapp.data.repository.WeatherForecastRepository
import com.rednerracaza.weatherapp.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewModel @Inject constructor(
    private val repository: WeatherForecastRepository
) : BaseViewModel() {

    private val forecastCitiesId = listOf("1701668,3067696,1835848")

    private val _weatherForecastListLiveData = MutableLiveData<List<WeatherForecastModel>>()

    val weatherForecastListLiveData: LiveData<List<WeatherForecastModel>> =
        _weatherForecastListLiveData

    fun loadWeatherForecastCities() {
        launchRequestWithErrorHandling {
            _weatherForecastListLiveData.value =
                repository.getWeatherForecastList(forecastCitiesId)
        }
    }
}