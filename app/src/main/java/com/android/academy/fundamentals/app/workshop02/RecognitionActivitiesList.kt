package com.android.academy.fundamentals.app.workshop02

import com.google.android.gms.location.ActivityTransition
import com.google.android.gms.location.DetectedActivity

// https://developer.android.com/guide/topics/location/transitions.html#register
object RecognitionActivities {
	fun list() = listOf(
		ActivityTransition.Builder()
			.setActivityType(DetectedActivity.STILL)
			.setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
			.build(),
		ActivityTransition.Builder()
			.setActivityType(DetectedActivity.STILL)
			.setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
			.build(),
		ActivityTransition.Builder()
			.setActivityType(DetectedActivity.WALKING)
			.setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
			.build(),
		ActivityTransition.Builder()
			.setActivityType(DetectedActivity.WALKING)
			.setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
			.build(),
		ActivityTransition.Builder()
			.setActivityType(DetectedActivity.RUNNING)
			.setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
			.build(),
		ActivityTransition.Builder()
			.setActivityType(DetectedActivity.RUNNING)
			.setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
			.build(),
		ActivityTransition.Builder()
			.setActivityType(DetectedActivity.ON_BICYCLE)
			.setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
			.build(),
		ActivityTransition.Builder()
			.setActivityType(DetectedActivity.ON_BICYCLE)
			.setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
			.build(),
		ActivityTransition.Builder()
			.setActivityType(DetectedActivity.IN_VEHICLE)
			.setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
			.build(),
		ActivityTransition.Builder()
			.setActivityType(DetectedActivity.IN_VEHICLE)
			.setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
			.build()
	)
	
	fun toActivityString(recognitionActivity: Int): String = when (recognitionActivity) {
		DetectedActivity.STILL -> "STILL"
		DetectedActivity.WALKING -> "WALKING"
		else -> "UNKNOWN"
	}
	
	fun toTransitionType(transitionType: Int): String = when (transitionType) {
		ActivityTransition.ACTIVITY_TRANSITION_ENTER -> "ENTER"
		ActivityTransition.ACTIVITY_TRANSITION_EXIT -> "EXIT"
		else -> "UNKNOWN"
	}
}