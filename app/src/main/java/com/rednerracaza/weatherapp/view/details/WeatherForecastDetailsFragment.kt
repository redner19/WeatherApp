package com.rednerracaza.weatherapp.view.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.rednerracaza.weatherapp.R
import com.rednerracaza.weatherapp.databinding.FragmentForecastDetailsBinding
import com.rednerracaza.weatherapp.util.getCityStatus
import com.rednerracaza.weatherapp.util.getFormattedTemp
import com.rednerracaza.weatherapp.util.viewLifecycle
import com.rednerracaza.weatherapp.view.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * [WeatherForecastDetailsFragment] - this fragment page shows the details of forecast selected recyclerview from api/server
 *
 * [viewModel] - this viewModel is shared to ForecastPage fragment.
 *
 * [binding] - viewBinding for this layout
 *
 * [lifecycleScope] - this lifecycleScope is tied to this fragment, if this fragment is destroyed it will cancelled it self.
 */
@AndroidEntryPoint
class WeatherForecastDetailsFragment : BaseFragment(R.layout.fragment_forecast_details) {
    override val viewModel by viewModels<WeatherForecastDetailsViewModel>()
    private var binding by viewLifecycle<FragmentForecastDetailsBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForecastDetailsBinding.bind(view)

        with(binding) {
            weatherForecastFavoriteCheckBox
                .setOnClickListener { viewModel.onClickFavorite() }

            viewModel.isFavoriteLiveData.observe(viewLifecycleOwner) {
                weatherForecastFavoriteCheckBox.isChecked = it
            }
        }

        viewModel.weatherUpdateLiveData.observe(viewLifecycleOwner) { forecast ->
            with(binding) {
                val main = forecast.main
                weatherForecastPlaceTextView.text = forecast.name
                weatherForecastStatusTextView.text = forecast.getCityStatus()
                weatherForecastTemperatureTextView.getFormattedTemp(main)
                weatherForecastHighLowTemperatureTextView
                    .getFormattedTemp(main, true)
            }
        }
    }
}