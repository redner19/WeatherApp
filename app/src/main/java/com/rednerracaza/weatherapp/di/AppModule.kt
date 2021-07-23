package com.rednerracaza.weatherapp.di

import com.rednerracaza.weatherapp.BuildConfig
import com.rednerracaza.weatherapp.data.local.WeatherForecastLocal
import com.rednerracaza.weatherapp.data.local.WeatherForecastLocalImpl
import com.rednerracaza.weatherapp.data.repository.WeatherForecastRepository
import com.rednerracaza.weatherapp.data.repository.WeatherForecastRepositoryImpl
import com.rednerracaza.weatherapp.data.service.WeatherForecastService
import com.rednerracaza.weatherapp.util.Constants.ForecastAppId
import com.rednerracaza.weatherapp.util.Constants.ForecastURL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit
        .Builder()
        .baseUrl(ForecastURL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Provides
    @Singleton
    fun providesOkhttpClient(): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor { chain ->
            val url = chain.request().url.newBuilder().apply {
                addQueryParameter(ForecastAppId, BuildConfig.FORECAST_API)
            }.build()
            val request = chain.request().newBuilder().url(url).build()
            return@addInterceptor chain.proceed(request)
        }.build()


    @Provides
    @Singleton
    fun provideForecastApi(retrofit: Retrofit): WeatherForecastService =
        retrofit.create(WeatherForecastService::class.java)

    @Provides
    @Singleton
    fun providesWeatherForecastRepository(
        WFL: WeatherForecastLocal,
        WFS: WeatherForecastService
    ): WeatherForecastRepository
    = WeatherForecastRepositoryImpl(WFL, WFS)

    @Provides
    @Singleton
    fun providesWeatherForecastLocal():WeatherForecastLocal = WeatherForecastLocalImpl()
}