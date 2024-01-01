package com.ndnam198.locationapp

import android.content.Context
import android.location.Address
import android.location.Geocoder
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.Locale

class LocationViewModel(private val context: Context) : ViewModel() {
    private val _location = mutableStateOf<LocationData?>(null)
    val location: State<LocationData?> = _location

    fun updateLocation(locationData: LocationData) {
        _location.value = locationData
    }
}