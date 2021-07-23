package com.rednerracaza.weatherapp.view.forecast

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rednerracaza.weatherapp.R
import com.rednerracaza.weatherapp.databinding.FragmentForecastBinding
import com.rednerracaza.weatherapp.util.viewLifecycle
import com.rednerracaza.weatherapp.view.base.BaseFragment
import com.rednerracaza.weatherapp.view.forecast.adapter.ForecastPageAdapter
import com.rednerracaza.weatherapp.view.forecast.adapter.OnItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherForecastFragment : BaseFragment(R.layout.fragment_forecast), OnItemClickListener {
    override val viewModel by viewModels<WeatherForecastViewModel>()
    private var binding by viewLifecycle<FragmentForecastBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForecastBinding.bind(view)

        val adapter = ForecastPageAdapter(this)

        with(binding) {
            weatherForecastListRecyclerView.adapter = adapter
            weatherForecastSwipeRefreshLayout.setOnRefreshListener {
                viewModel.loadWeatherForecastCities()
            }
        }

        with(viewModel){
            weatherForecastListLiveData.observe(viewLifecycleOwner) { forecastList ->
                binding.weatherForecastSwipeRefreshLayout
                    .isRefreshing = false
                adapter.submitList(forecastList)
            }

            loadWeatherForecastCities()
        }
    }

    override fun onItemClicked(id : String) =
        findNavController().navigate(
            WeatherForecastFragmentDirections
                .actionForecastPageToForecastDetailsPage(id)
        )
}