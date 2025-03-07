package com.example.weatherappjpc.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BaseURL ="https://api.weatherapi.com"

//    val api: ApiService by lazy {
//        Retrofit.Builder()
//            .baseUrl(BaseURL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApiService::class.java)
//    }

    private fun getInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val weatherApi :ApiService by lazy {
        getInstance().create(ApiService::class.java)
    }
}