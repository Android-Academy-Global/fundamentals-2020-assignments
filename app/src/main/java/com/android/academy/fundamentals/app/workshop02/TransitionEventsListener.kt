package com.android.academy.fundamentals.app.workshop02

import com.google.android.gms.location.ActivityTransitionEvent

interface TransitionEventsListener {
	fun onEvents(events: List<ActivityTransitionEvent>)
}