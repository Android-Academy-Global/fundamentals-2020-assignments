package com.android.academy.fundamentals.app.workshop02.solution

import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.academy.fundamentals.app.BuildConfig
import com.android.academy.fundamentals.app.workshop02.RecognitionActivities
import com.android.academy.fundamentals.app.workshop02.TransitionEventsListener
import com.android.academy.fundamentals.app.workshop02.TransitionsReceiver
import com.google.android.gms.location.*
import java.text.SimpleDateFormat
import java.util.*

class Ws02BoundedService : Service() {
	
	private val binder = Ws02Binder()
	
	private val transitionEventsListener = object : TransitionEventsListener {
		override fun onEvents(events: List<ActivityTransitionEvent>) {
			Log.d(TAG, "onEvents")
			val reportSB = StringBuilder()
			events.forEach {
				reportSB.append(
					"Transition:${RecognitionActivities.toActivityString(it.activityType)}" +
							" (${RecognitionActivities.toTransitionType(it.transitionType)})" +
							" ${SimpleDateFormat("HH:mm:ss", Locale.US).format(Date())}\n"
				)
			}
			_data.value = reportSB.toString()
		}
	}
	private val transitionsReceiver = TransitionsReceiver(
		TRANSITIONS_RECEIVER_ACTION,
		transitionEventsListener
	)
	private var isRegistered = false
	private var isActivityTrackingEnabled = false
	private lateinit var activityTransitionsList: List<ActivityTransition>
	private lateinit var activityTransitionsPI: PendingIntent
	
	private val _data = MutableLiveData("")
	private val data: LiveData<String> get() = _data
	
	override fun onCreate() {
		Log.d(TAG, "onCreate")
		
		setupRecognitionActivitiesList()
		setupBroadcastPendingIntent()
	}
	
	override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
		Log.d(TAG, "onStartCommand STICKY flags:$flags, startId:$startId, intent:$intent")
		return START_STICKY
	}
	
	override fun onBind(intent: Intent?): IBinder {
		Log.d(TAG, "onBind intent:$intent")
		return binder
	}
	
	override fun onRebind(intent: Intent?) {
		Log.d(TAG, "onRebind")
		super.onRebind(intent)
	}
	
	override fun onUnbind(intent: Intent?): Boolean {
		val flag = super.onUnbind(intent)
		Log.d(TAG, "onUnbind return:$flag, intent:$intent")
		
		unregisterTransitionReceiver()
		disableActivityTransitions()
		
		return flag
	}
	
	override fun onDestroy() {
		Log.d(TAG, "onDestroy")
	}
	
	fun startWork(hasRecognitionPermission: Boolean) {
		Log.d(TAG, "startWork hasPermission:$hasRecognitionPermission")
		
		if (hasRecognitionPermission) {
			registerTransitionReceiver()
			enableActivityTransitions()
		}
	}
	
	fun observableData(): LiveData<String> = data
	
	private fun setupRecognitionActivitiesList() {
		activityTransitionsList = RecognitionActivities.list()
	}
	
	private fun setupBroadcastPendingIntent() {
		activityTransitionsPI = PendingIntent.getBroadcast(
			this,
			0,
			Intent(TRANSITIONS_RECEIVER_ACTION),
			0
		)
	}
	
	private fun registerTransitionReceiver() {
		Log.d(TAG, "registerTransitionReceiver")
		registerReceiver(transitionsReceiver, IntentFilter(TRANSITIONS_RECEIVER_ACTION))
		isRegistered = true
	}
	
	private fun unregisterTransitionReceiver() {
		Log.d(TAG, "unregisterTransitionReceiver isRegistered:$isRegistered")
		if (isRegistered) {
			unregisterReceiver(transitionsReceiver)
			isRegistered = false
		}
	}
	
	/**
	 * Registers callbacks for [ActivityTransition] events via a custom [BroadcastReceiver]
	 */
	private fun enableActivityTransitions() {
		Log.d(TAG, "enableActivityTransitions isTrackingEnabled:$isActivityTrackingEnabled")
		
		if (isActivityTrackingEnabled) return
		
		// Register for Transitions Updates.
		ActivityRecognition.getClient(this)
			.requestActivityTransitionUpdates(
				ActivityTransitionRequest(activityTransitionsList),
				activityTransitionsPI
			)
			.addOnSuccessListener {
				isActivityTrackingEnabled = true
				Log.d(TAG, "enableActivityTransitions Transitions Api was successfully registered")
			}
			.addOnFailureListener { e ->
				Log.e(TAG, "enableActivityTransitions Transitions Api could NOT be registered:$e")
			}
	}
	
	/**
	 * Unregisters callbacks for [ActivityTransition] events via a custom [BroadcastReceiver]
	 */
	private fun disableActivityTransitions() {
		Log.d(TAG, "disableActivityTransitions isTrackingEnabled:$isActivityTrackingEnabled")
		
		if (!isActivityTrackingEnabled) return
		
		ActivityRecognition.getClient(this)
			.removeActivityTransitionUpdates(activityTransitionsPI)
			.addOnSuccessListener {
				isActivityTrackingEnabled = false
				Log.d(TAG, "disableActivityTransitions Transitions successfully unregistered")
			}
			.addOnFailureListener { e ->
				Log.e(TAG, "enableActivityTransitions Transitions could not be unregistered:$e")
			}
	}
	
	inner class Ws02Binder : Binder() {
		fun getService(): Ws02BoundedService {
			return this@Ws02BoundedService
		}
	}

	
	companion object {
		private const val TAG = "WS02::SERVICE"
		private const val TRANSITIONS_RECEIVER_ACTION =
			"${BuildConfig.APPLICATION_ID} TRANSITIONS_RECEIVER_ACTION"
	}
}