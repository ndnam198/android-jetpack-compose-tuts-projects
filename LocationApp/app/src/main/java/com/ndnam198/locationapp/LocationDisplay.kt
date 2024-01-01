package com.ndnam198.locationapp

import android.Manifest
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat

@Composable
fun LocationDisplay(context: Context, locationUtils: LocationUtils, viewModel: LocationViewModel) {
    val location = viewModel.location.value

    val address = viewModel.location.value?.let {
        locationUtils.reverseGeocodeLocation(it)
    }

    val requestPermissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions(),
            onResult = { results: Map<String, @JvmSuppressWildcards Boolean> ->
                if (results[Manifest.permission.ACCESS_FINE_LOCATION] == true && results[Manifest.permission.ACCESS_COARSE_LOCATION] == true) {
                    // Permission Accepted: Do something
                    locationUtils.requestPermissionUpdates(viewModel = viewModel)
                } else {
                    // Permission Denied: Do something
                    val rationaleRequired = ActivityCompat.shouldShowRequestPermissionRationale(
                        context as MainActivity, Manifest.permission.ACCESS_FINE_LOCATION
                    ) || ActivityCompat.shouldShowRequestPermissionRationale(
                        context, Manifest.permission.ACCESS_COARSE_LOCATION
                    )

                    if (rationaleRequired) {
                        Toast.makeText(
                            context,
                            "Location permission is required to get current geographical information and convert to address accordingly",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Please grant Location permission in Android Settings",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        if (location != null) {
            Text("Latitude: ${location.latitude}, Longitude: ${location.longitude}, Address: $address")
        } else {
            Text("Location is not available")
        }

        Button(onClick = {
            if (locationUtils.hasLocationPermission(context)) {
                // Permission granted
                locationUtils.requestPermissionUpdates(viewModel = viewModel)
            } else {
                // No permission yet, ask for it
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }) {
            Text("Get Location")
        }
    }
}