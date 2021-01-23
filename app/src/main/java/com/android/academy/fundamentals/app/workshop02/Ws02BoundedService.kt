package com.android.academy.fundamentals.app.workshop02

import android.app.Service
import android.content.Intent
import android.hardware.*
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.LiveData
import java.util.*
import kotlin.properties.Delegates

class Ws02BoundedService : Service() {
	
	private val binder = Ws02Binder()
	private val enableRebind = true
	
	private var sensorManager: Ws02SensorManager by Delegates.notNull()
	
	override fun onCreate() {
		Log.d(TAG, "onCreate")
		
		setupSensorManager()
	}
	
	override fun onBind(intent: Intent?): IBinder {
		Log.d(TAG, "onBind intent:$intent")
		registerSensors()
		
		return binder
	}
	
	override fun onRebind(intent: Intent?) {
		Log.d(TAG, "onRebind intent:$intent")
		registerSensors()
	}
	
	override fun onUnbind(intent: Intent?): Boolean {
		Log.d(TAG, "onUnbind enableRebind:$enableRebind, intent:$intent")
		
		unregisterSensors()
		
		return enableRebind
	}
	
	override fun onDestroy() {
		Log.d(TAG, "onDestroy")
	}
	
	private fun setupSensorManager() {
		sensorManager = Ws02SensorManager(this)
	}
	
	private fun registerSensors() {
		sensorManager.registerSensors()
	}
	
	private fun unregisterSensors() {
		sensorManager.unregisterSensors()
	}
	
	fun observableData(): LiveData<FloatArray> = sensorManager.observableData()
	
	inner class Ws02Binder : Binder() {
		fun getService(): Ws02BoundedService {
			return this@Ws02BoundedService
		}
	}
	
	companion object {
		private const val TAG = "WS02::SERVICE"
	}
}