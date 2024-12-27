package com.example.dz16retrofitweatherapi

import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.location.LocationManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val apiKey = context.getString(R.string.api)

    private val locationManager: LocationManager =
        context.getSystemService(LOCATION_SERVICE) as LocationManager

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(locationManager = locationManager, apiKey = apiKey) as T
    }

}