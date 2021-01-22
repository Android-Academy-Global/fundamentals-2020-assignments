package com.android.academy.fundamentals.app.workshop02.solution

import android.app.Service
import android.content.Intent
import android.hardware.*
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

// https://developer.android.com/guide/topics/sensors/sensors_position?hl=en
class Ws02SolutionBoundedService : Service(), SensorEventListener {
	
	private val binder = Ws02Binder()
	private val enableRebind = true
	
	private lateinit var sensorManager: SensorManager
	private val accelerometerReading = FloatArray(3)
	private val magnetometerReading = FloatArray(3)
	private val rotationMatrix = FloatArray(9)
	
	private val _data = MutableLiveData("")
	private val data: LiveData<String> get() = _data
	
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
	
	override fun onSensorChanged(event: SensorEvent?) {
		when (event?.sensor?.type) {
			Sensor.TYPE_ACCELEROMETER -> {
				System.arraycopy(
					event.values.clone(),
					0,
					accelerometerReading,
					0,
					accelerometerReading.size
				)
				
				updateOrientationAngles()
			}
			Sensor.TYPE_MAGNETIC_FIELD -> {
				System.arraycopy(
					event.values.clone(),
					0,
					magnetometerReading,
					0,
					magnetometerReading.size
				)
				
				updateOrientationAngles()
			}
			null -> {
			}
		}
	}
	
	override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
	}
	
	fun observableData(): LiveData<String> = data
	
	private fun setupSensorManager() {
		sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
	}
	
	private fun registerSensors() {
		sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also { accelerometer ->
			sensorManager.registerListener(
				this,
				accelerometer,
				SensorManager.SENSOR_DELAY_UI,
				SensorManager.SENSOR_DELAY_UI
			)
		}
		sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)?.also { magneticField ->
			sensorManager.registerListener(
				this,
				magneticField,
				SensorManager.SENSOR_DELAY_UI,
				SensorManager.SENSOR_DELAY_UI
			)
		}
	}
	
	private fun unregisterSensors() {
		sensorManager.unregisterListener(this)
	}
	
	private fun updateOrientationAngles() {
		val success = SensorManager.getRotationMatrix(
			rotationMatrix,
			null,
			accelerometerReading,
			magnetometerReading
		)
		
		if (!success) {
			return
		}
		
		// values[0]: Azimuth, values[1]: Pitch, values[2]: Roll in radian
		val calculated = FloatArray(3)
		SensorManager.getOrientation(rotationMatrix, calculated)
		_data.value = """Angle degrees
			azimuth: ${calculated[0].toDegrees()}
			pitch: ${calculated[1].toDegrees()}
			roll: ${calculated[2].toDegrees()}
		""".trim()
	}
	
	private fun Float.toDegrees() = (Math.toDegrees(this.toDouble()) * 100).toInt() / 100f
	
	inner class Ws02Binder : Binder() {
		fun getService(): Ws02SolutionBoundedService {
			return this@Ws02SolutionBoundedService
		}
	}
	
	companion object {
		private const val TAG = "WS02::SERVICE"
	}
}