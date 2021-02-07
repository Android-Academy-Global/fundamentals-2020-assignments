package com.example.android.people.data

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

interface LocationProvider {
    @RequiresPermission(ACCESS_COARSE_LOCATION)
    suspend fun getUserLocation(): GetUserLocationResult
}

sealed class GetUserLocationResult {
    data class Success(val location: Location) : GetUserLocationResult()
    sealed class Failed : GetUserLocationResult() {
        object LocationManagerNotAvailable : Failed()
        object LocationProviderIsDisabled : Failed()
        object OtherFailure : Failed()
    }
}

class FusedLocationProvider(private val context: Context) : LocationProvider {

    private val TAG = "FusedLocationProvider"

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    @RequiresPermission(ACCESS_COARSE_LOCATION)
    override suspend fun getUserLocation(): GetUserLocationResult {
        val lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
            ?: return GetUserLocationResult.Failed.LocationManagerNotAvailable

        if (!isLocationEnabled(lm)) {
            return GetUserLocationResult.Failed.LocationProviderIsDisabled
        }

        return requestLastKnownLocation()
    }

    // Not the best request type. Please read google recommendations:
    // https://developer.android.com/training/location?hl=hi
    @RequiresPermission(ACCESS_COARSE_LOCATION)
    private suspend fun requestLastKnownLocation(): GetUserLocationResult {
        return suspendCancellableCoroutine<GetUserLocationResult> { continuation ->
            val lastLocation = fusedLocationClient.lastLocation
            lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    continuation.resume(GetUserLocationResult.Success(location))
                } else {
                    continuation.resume(GetUserLocationResult.Failed.OtherFailure)
                }
            }.addOnFailureListener {
                Log.d(TAG, "onFailureListener", it)
                continuation.resume(GetUserLocationResult.Failed.OtherFailure)
            }
        }
    }

    private fun isLocationEnabled(lm: LocationManager): Boolean {
        var gpsEnabled = false
        var networkEnabled = false

        try {
            gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
        } catch (e: Exception) {
        }

        try {
            networkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        } catch (e: Exception) {
        }

        return gpsEnabled || networkEnabled
    }
}