package com.example.weatherappjpc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.weatherappjpc.ui.theme.WeatherAPPJPCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        setContent {
            WeatherAPPJPCTheme {
                HomeScreen(weatherViewModel)
            }
        }
    }
}

