package com.example.android.people.ui

import android.location.Location

interface MyLocationProviderListener {
	fun onLocationManagerNotAvailable()
	fun onLocationProviderDisabled()
	fun onLocationRequestSuccess(location: Location?)
	fun onLocationRequestFailure(withException: Boolean)
}