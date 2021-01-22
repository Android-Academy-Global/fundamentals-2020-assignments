package com.android.academy.fundamentals.app.workshop02

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import com.google.android.gms.location.ActivityTransitionResult

// https://developer.android.com/codelabs/activity-recognition-transition?hl=ru#0
class TransitionsReceiver(
	private val action: String,
	private val listener: TransitionEventsListener
) : BroadcastReceiver() {
	
	override fun onReceive(context: Context?, intent: Intent?) {
		Log.d(TAG, "onReceive intent:$intent")
		
		if (!TextUtils.equals(action, intent?.action)) {
			Log.e(TAG, "onReceive unsupported action in TransitionsReceiver:${intent?.action}")
			return
		}
		
		if (ActivityTransitionResult.hasResult(intent)) {
			val events = ActivityTransitionResult.extractResult(intent)?.transitionEvents
			Log.d(TAG, "onReceive hasResults events:${events?.size}")
			events?.let {
				listener.onEvents(it)
			}
		}
	}
	
	companion object {
		private const val TAG = "WS02::RECEIVER"
	}
}