package com.example.android.people.ui

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MyLocationProvider {
	
	private var fusedLocationClient: FusedLocationProviderClient? = null
	private var listener: MyLocationProviderListener? = null
	
	fun create(activity: Activity, listener: MyLocationProviderListener) {
		this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
		this.listener = listener
	}
	
	fun destroy() {
		fusedLocationClient = null
		listener = null
	}
	
	fun checkLocationProviderEnabled(context: Context) {
		val lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
		if (lm == null) {
			listener?.onLocationManagerNotAvailable()
			return
		}
		
		var gps_enabled = false
		var network_enabled = false
		try {
			gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
			
		} catch (e: Exception) {
			e.printStackTrace()
		}
		
		try {
			network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
			
		} catch (e: Exception) {
			e.printStackTrace()
		}
		
		// This way detects only if providers disabled.
		// But ignore all others issues.
		if (!gps_enabled && !network_enabled) {
			listener?.onLocationProviderDisabled()
			
		} else {
			requestLastKnownLocation(context)
		}
	}
	
	// Not the best request type. Please read google recommendations:
	// https://developer.android.com/training/location?hl=hi
	private fun requestLastKnownLocation(context: Context) {
		if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
			== PackageManager.PERMISSION_GRANTED) {
			
			fusedLocationClient?.lastLocation
				?.addOnSuccessListener { location: Location? ->
					listener?.onLocationRequestSuccess(location)
				}
				?.addOnFailureListener {
					listener?.onLocationRequestFailure(withException = true)
				}
		}
	}
}