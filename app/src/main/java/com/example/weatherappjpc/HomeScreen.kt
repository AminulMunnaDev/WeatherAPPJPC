package com.example.weatherappjpc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherappjpc.Api.NetworkResponse
import com.example.weatherappjpc.DataClass.WeatherModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: WeatherViewModel) {
    var city by remember {
        mutableStateOf("")
    }
    val weatherResult = viewModel.weatherResult.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceEvenly
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = city,
                onValueChange = {
                    city = it
                },
                label = { Text(text = "Enter your City") },
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),  // ✅ Force text color
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,  // Transparent background when focused
                    unfocusedContainerColor = Color.Transparent, // Transparent when not focused
                    focusedIndicatorColor = Color.Black,       // ✅ Border color when focused
                    unfocusedIndicatorColor = Color.Gray,     // ✅ Border color when not focused
                    focusedLabelColor = Color.Black,          // ✅ Label color when typing (focused)
                    unfocusedLabelColor = Color.Black,          // ✅ Label color when not typing (unfocused)
                    cursorColor = Color.Black,                 // ✅ Cursor color
                    focusedTextColor = Color.Black,           // ✅ Input text color when focused
                    unfocusedTextColor = Color.DarkGray       // ✅ Input text color when unfocused
                )
            )
            IconButton(onClick = {
                viewModel.getData(city)
            }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        }
        when (val result = weatherResult.value) {
            is NetworkResponse.Error -> {
                Text(text = result.message)
            }

            NetworkResponse.Loading -> {
                CircularProgressIndicator()
            }

            is NetworkResponse.Success -> {
                WeatherDetails(data = result.data)
            }

            null -> {

            }
        }

    }

}

@Composable
fun WeatherDetails(data: WeatherModel) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally // Centers everything horizontally
    ) {
        // First Row: Location Icon
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "Location",
            modifier = Modifier.size(38.dp),
            tint = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(8.dp)) // Space between icon and text

        // Second Row: City and Country
        Row(
            modifier = Modifier.padding(top = 4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(data.location.name, fontSize = 24.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                data.location.country, fontSize = 24.sp, style =
                androidx.compose.ui.text.TextStyle(color = Color.Gray)
            )

        }
        Spacer(modifier = Modifier.height(12.dp)) // Space between text and temperature()
        Text(
            data.current.temp_c.toString() + "°C",
            fontSize = 70.sp, color = Color.DarkGray
        )
    }
    Text(
        data.current.temp_f.toString() + "°F",
        fontSize = 30.sp, color = Color.Gray
    )
}


