package com.android.academy.fundamentals.app.workshop02

import android.app.Service
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

// https://developer.android.com/guide/topics/sensors/sensors_position?hl=en
class Ws02SensorManager(context: Context) : SensorEventListener {
	
	private var sensorManager: SensorManager =
		context.getSystemService(Service.SENSOR_SERVICE) as SensorManager
	
	private val accelerometerReading = FloatArray(3)
	private val magnetometerReading = FloatArray(3)
	private val rotationMatrix = FloatArray(9)
	private var prevAzimuth = 0.0f
	private var azimuthNoise = 0.01f
	
	private val data = MutableLiveData(floatArrayOf(0.0f, 0.0f, 0.0f))
	
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
				
				calculateOrientationAngles()
			}
			Sensor.TYPE_MAGNETIC_FIELD -> {
				System.arraycopy(
					event.values.clone(),
					0,
					magnetometerReading,
					0,
					magnetometerReading.size
				)
				
				calculateOrientationAngles()
			}
			null -> {
			}
		}
	}
	
	override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
	}
	
	fun registerSensors() {
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
	
	fun unregisterSensors() {
		sensorManager.unregisterListener(this)
	}
	
	fun observableData(): LiveData<FloatArray> = data
	
	// This is correct only for "Portrait" device orientation.
	private fun calculateOrientationAngles() {
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
		
		handleOrientationUpdate(calculated)
	}
	
	private fun handleOrientationUpdate(angles: FloatArray) {
		val azimuth = angles[0]
		if (azimuth < prevAzimuth - azimuthNoise || azimuth > prevAzimuth + azimuthNoise) {
			data.value = angles
		}
		
		prevAzimuth = azimuth
	}
}