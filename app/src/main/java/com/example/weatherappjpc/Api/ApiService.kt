package com.example.weatherappjpc.Api

import com.example.weatherappjpc.DataClass.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/v1/current.json")
    suspend fun getWeather(
        @Query("key") apikey: String,
        @Query("q") city: String,
    ): Response<WeatherModel>
}