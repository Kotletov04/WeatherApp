package com.example.weatherapp.ui.components.city

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.models.ItemsModel
import com.example.weatherapp.domain.usecases.SaveCurrentCityUseCase
import com.example.weatherapp.domain.usecases.SearchCityUseCase
import com.example.weatherapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class CityViewModel @Inject constructor(
    private val searchCityUseCase: SearchCityUseCase,
    private val saveCurrentCityUseCase: SaveCurrentCityUseCase
): ViewModel() {

    val cityField = mutableStateOf("")
    val textFieldIsActive = mutableStateOf(false)
    private val _state = mutableStateOf(CityState())
    val state: State<CityState> = _state


    fun getCity() {
        if (cityField.value != "") searchCityUseCase.invoke(city = cityField.value).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = CityState(error = result.message)
                }
                is Resource.Loading -> {
                    _state.value = CityState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = CityState(city = result.data!!.items)
                }
            }
        }.launchIn(viewModelScope)

    }

    fun saveCity(item: ItemsModel) {
        saveCurrentCityUseCase.invoke(item = item).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = CityState(error = result.message)
                }
                is Resource.Loading -> {
                    _state.value = CityState(isLoading = true)
                }
                is Resource.Success -> {
                }
            }
        }.launchIn(viewModelScope)
    }

    fun clearErrorMessage() {
        _state.value = _state.value.copy(error = "")
    }
}