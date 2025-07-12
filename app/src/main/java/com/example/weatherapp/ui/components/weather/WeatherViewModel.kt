package com.example.weatherapp.ui.components.weather

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.usecases.GetCurrentWeatherUseCase
import com.example.weatherapp.domain.usecases.GetForecastWeatherUseCase
import com.example.weatherapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getForecastWeatherUseCase: GetForecastWeatherUseCase
): ViewModel() {

    private val _state = mutableStateOf(WeatherState())
    val state: State<WeatherState> = _state

    private val _forecastState = mutableStateOf(ForecastWeatherState())
    val forecastState: State<ForecastWeatherState> = _forecastState

    val isRefreshing = mutableStateOf(false)

    fun getCurrentWeather(city: String) {
        getCurrentWeatherUseCase.invoke(city = city).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = WeatherState(error = result.message)
                }
                is Resource.Loading -> {
                    _state.value = WeatherState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = WeatherState(currentWeather = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getForecastWeather(city: String) {
        getForecastWeatherUseCase.invoke(city = city).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _forecastState.value = ForecastWeatherState(error = result.message)
                }
                is Resource.Loading -> {
                    _forecastState.value = ForecastWeatherState(isLoading = true)
                }
                is Resource.Success -> {
                    _forecastState.value = ForecastWeatherState(forecastWeather = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun refresh(city: String) {
        setRefreshing()
        getCurrentWeather(city = city)
        getForecastWeather(city = city)
        clearRefreshing()
    }

    private fun setRefreshing() {
        isRefreshing.value = true
    }

    private fun clearRefreshing() {
        isRefreshing.value = false
    }

}