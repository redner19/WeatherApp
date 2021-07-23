package com.rednerracaza.weatherapp.view.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment(fragmentLayout: Int) : Fragment(fragmentLayout) {
    protected abstract val viewModel: BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.showErrorLiveData.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), getText(error), Toast.LENGTH_LONG).show()
        }
    }
}