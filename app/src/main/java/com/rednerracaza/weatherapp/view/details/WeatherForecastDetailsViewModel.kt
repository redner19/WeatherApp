package com.rednerracaza.weatherapp.view.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.rednerracaza.weatherapp.data.model.WeatherForecastModel
import com.rednerracaza.weatherapp.data.repository.WeatherForecastRepository
import com.rednerracaza.weatherapp.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherForecastDetailsViewModel @Inject constructor(
    private val repository: WeatherForecastRepository,
    state : SavedStateHandle
) : BaseViewModel() {

    private val _weatherUpdateLiveData = MutableLiveData<WeatherForecastModel>()

    val weatherUpdateLiveData: LiveData<WeatherForecastModel> get() = _weatherUpdateLiveData

    private val _isFavoriteLiveData = MutableLiveData<Boolean>()

    val isFavoriteLiveData: LiveData<Boolean> = _isFavoriteLiveData

    private val _cityId = state.get<String>("weatherForecastCityId").orEmpty()

    init { loadCityDetails() }

    private fun loadCityDetails(){
        launchRequestWithErrorHandling {
            _weatherUpdateLiveData.value = repository.getWeatherForecastDetails(_cityId).also {
                _isFavoriteLiveData.value = it.isFavorite
            }
        }
    }

    fun onClickFavorite() = viewModelScope.launch {
        repository.toggleFavorite(_cityId)
        _isFavoriteLiveData.apply {
            value = value?.not()
        }
    }
}