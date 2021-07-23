package com.rednerracaza.weatherapp.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.rednerracaza.weatherapp.R
import com.rednerracaza.weatherapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * [MainActivity] - This activity is the host of the two fragments
 *
 * [navController] - The NavController whose navigation actions will be reflected in the title of the action bar.
 *
 * [binding] - viewBinding for this layout
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()

        binding.apply {
            setSupportActionBar(toolbar)
            setupActionBarWithNavController(navController)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}