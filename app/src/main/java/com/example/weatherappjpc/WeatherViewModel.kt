package com.example.weatherappjpc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappjpc.Api.ConstApiKey
import com.example.weatherappjpc.Api.NetworkResponse
import com.example.weatherappjpc.Api.RetrofitInstance
import com.example.weatherappjpc.Api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {


    val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherResult: LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    fun getData(city: String) {
        viewModelScope.launch {
            try {
                _weatherResult.value = NetworkResponse.Loading
                val response = weatherApi.getWeather(ConstApiKey.apiKey, city)

                if (response.isSuccessful) {
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Success(it)
                    }
                } else {
                    _weatherResult.value =NetworkResponse.Error("Failed to load Data")
                }
            } catch (e: Exception) {
                _weatherResult.value =NetworkResponse.Error("Failed to load Data")
            }

        }

    }
}