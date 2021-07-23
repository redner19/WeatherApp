package com.rednerracaza.weatherapp.view.forecast

import android.accounts.NetworkErrorException
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.test
import com.rednerracaza.weatherapp.R
import com.rednerracaza.weatherapp.data.local.WeatherForecastLocal
import com.rednerracaza.weatherapp.data.repository.WeatherForecastRepositoryImpl
import com.rednerracaza.weatherapp.data.service.WeatherForecastService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException


class WeatherForecastViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var mockRepository: WeatherForecastRepositoryImpl

    private val MOCK_WEATHER_CITY_IDS = listOf("1701668", "3067696", "1835848")

    @Before
    fun setup() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        MockKAnnotations.init(this)
        val mockService = mockk<WeatherForecastService>()
        val mockRepo = mockk<WeatherForecastLocal>()

        mockRepository = WeatherForecastRepositoryImpl(mockRepo,mockService)
    }


    @Test
    fun `when app returns no connection, then show no connection message`() {
        val expectedResult = R.string.weather_forecast_no_connection
        val mockResponse = mockk<NetworkErrorException>()

        coEvery { mockRepository
            .getWeatherForecastList(MOCK_WEATHER_CITY_IDS)
        } throws mockResponse

        val mockViewModel = WeatherForecastViewModel(mockRepository)

        mockViewModel.loadWeatherForecastCities()

        mockViewModel
            .showErrorLiveData
            .test()
            .assertValue(expectedResult)
            .assertHistorySize(1)
    }

    @Test
    fun `when list of weather forecast returns bad response, then show something went wrong message`() {
        val expectedResult = R.string.weather_forecast_http_error
        val mockResponse = mockk<HttpException>()

        coEvery {
            mockRepository
                .getWeatherForecastList(MOCK_WEATHER_CITY_IDS)
        } throws mockResponse

        val mockViewModel = WeatherForecastViewModel(mockRepository)

        mockViewModel.loadWeatherForecastCities()

        mockViewModel
            .showErrorLiveData
            .test()
            .assertValue(expectedResult)
            .assertHistorySize(1)
    }
}