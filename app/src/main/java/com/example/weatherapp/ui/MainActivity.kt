package com.example.weatherapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.weatherapp.ui.components.city.CityComponent
import com.example.weatherapp.ui.components.city.CityViewModel
import com.example.weatherapp.ui.components.weather.WeatherComponent
import com.example.weatherapp.ui.components.weather.WeatherViewModel
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.ui.nav.CityRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            WeatherAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "CityScreen") {
                    composable("CityScreen") { CityScreen(navController = navController) }
                    composable<CityRoute> { backStackEntry ->
                        val city = backStackEntry.toRoute<CityRoute>()
                        WeatherScreen(city = city.cityName, navController = navController)
                    }
                }
            }
        }
    }


    @Composable
    private fun WeatherScreen(city: String, navController: NavController) {
        val weatherViewModel: WeatherViewModel by viewModels()
        weatherViewModel.getCurrentWeather(city = city)
        weatherViewModel.getForecastWeather(city = city)
        WeatherComponent(weatherViewModel = weatherViewModel, city = city, navController = navController)


    }

    @Composable
    private fun CityScreen(navController: NavController) {
        val cityViewModel: CityViewModel by viewModels()
        CityComponent(cityViewModel = cityViewModel, navController = navController)
    }


}
